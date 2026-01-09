#!/bin/bash
set -e

ASADMIN="/opt/payara/appserver/bin/asadmin --user admin --passwordfile=/opt/payara/passwordFile"

# 1. Start Payara
echo "--> Starting Payara Server..."
asadmin start-domain

# 2. Wait for Admin Console
echo "--> Waiting for Admin Console..."
while ! nc -z localhost 4848; do   
  sleep 1
done
echo "--> Admin Console is UP."

# 3. Create Connection Pool (DIRECT COMMAND)
echo "--> Creating Connection Pool..."
$ASADMIN create-jdbc-connection-pool \
    --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource \
    --restype javax.sql.DataSource \
    --property user=bank_user:password=bank_pass:serverName=mysql:portNumber=3306:databaseName=banking_db:useSSL=false:allowPublicKeyRetrieval=true \
    Bank_System_Connection_Pool

# 4. Create JDBC Resource (DIRECT COMMAND)
# We removed '--target server' to use the default domain target, which is safer
echo "--> Creating JDBC Resource..."
$ASADMIN create-jdbc-resource \
    --connectionpoolid Bank_System_Connection_Pool \
    jdbc/bank_system_connection

# 5. Verify Everything
echo "--> Verifying Configuration..."
if $ASADMIN ping-connection-pool Bank_System_Connection_Pool; then
    echo "    [OK] Pool is reachable."
else
    echo "    [ERR] Pool Ping Failed!"
    exit 1
fi

echo "--> Checking Resource List..."
# We grep to ensure our resource is actually there. If not, we fail.
if $ASADMIN list-jdbc-resources | grep -q "jdbc/bank_system_connection"; then
    echo "    [OK] Resource 'jdbc/bank_system_connection' exists."
else
    echo "    [ERR] Resource MISSING from server registry!"
    exit 1
fi

# 6. Deploy Application
echo "--> Deploying EAR..."
$ASADMIN deploy --force=true /tmp/Banking-System.ear

# 7. Keep Alive
echo "--> Tailing Logs..."
tail -f $PAYARA_HOME/glassfish/domains/$DOMAIN_NAME/logs/server.log
