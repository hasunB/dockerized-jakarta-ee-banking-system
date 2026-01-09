FROM payara/server-full:6.2024.3-jdk17

ENV PAYARA_HOME=/opt/payara/appserver
ENV DOMAIN_NAME=domain1

# 0. Install netcat
USER root
RUN apt-get update && apt-get install -y netcat-openbsd
USER payara

# 1. Copy MySQL Driver
COPY docker/lib/mysql-connector-j-8.3.0.jar $PAYARA_HOME/glassfish/domains/$DOMAIN_NAME/lib/

# 2. Copy EAR to /tmp
COPY --chown=payara:payara ear/target/*.ear /tmp/Banking-System.ear

# 3. Copy RUN Script (We removed setup.asadmin)
COPY docker/run_payara.sh $PAYARA_HOME/run_payara.sh

# 4. Set Permissions
USER root
RUN chmod +x $PAYARA_HOME/run_payara.sh
USER payara

# 5. Start
CMD ["/opt/payara/appserver/run_payara.sh"]