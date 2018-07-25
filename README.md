# Project Purpose
Working on a big legacy JSF project. While that is at 1.1 and customised I want to do a 1.2 JSF project just to get myself familiar with the technology. Also use it with higher versions of spring and Java 8 (never a bad thing)

Also trying out the pattern described in https://www.petrikainulainen.net/software-development/design/understanding-spring-web-application-architecture-the-classic-way/ even though I could just use domain objects everywhere for this size of "model" application. Here I will use a DTO defined in the service layer that are internally mapped to domain objects using http://mapstruct.org/

Just use Spring security to force a login (using the Spring default login page) and get the username for use within the application 

Just get this working in STS

# Project Scope
* The project covers a bread shop where the owner can enrol customers to allow them to reserve breads
* Customers must login to reserve bread
* Dynamically add up the totals using JavaScript (jQuery)
* Customers can cancel a bread reservation (but only while they are in reserve)
* There is a limit of how much bread a customer can reserve (if they want more they need to make a phone or personal call)
* Customers can see their current reservation
* The owner (not implemented) has a sub-system where:
    - They set how much bread can be reserved.
    - MIS on the value of bread sold via reservations, the value of cancellations and top customers for both sales and cancellations


# Versions Used

Using references:
* https://en.wikipedia.org/wiki/Java_EE_version_history 
* http://www.oracle.com/webfolder/technetwork/jsc/xml/ns/javaee/index.html#5

Versions Used
Java 8:
Servlet 4.0
JSF 1.2. Pretty Faces is used to get friendly URLs
JSP 2.1
JSTL 1.2
Spring 5 (using Java Config)

Test with TC Server 4 (Tomcat 9)


# Local Test URL

http://localhost:8080/breadshop/reserve - reserve bread journey


# Improve URL using pretty faces
see <http://www.ocpsoft.org/prettyfaces/>. This requires a pretty-config.xml

URL (under breadshop):
/reserve - make a reservations (if one is present then show review screen)
/review - review reservations ( edit / cancel). After specified time this is just a read only item for confirmation with a message that a new reservation can be made next working day

# Schema

See create-db.sql file

MIS Would be handled by a set of historic tables created from the above transactional data tables.



# Run within STS
Had to set Working directory from default to /home/steve/software/sts/3.9.4/sts-bundle/pivotal-tc-server/developer-4.0.0.RELEASE - otherwise "Could not find or load main class org.apache.catalina.startup.Bootstrap site:stackoverflow.com" when starting

Remember to add the project to the TC Server

Needed to change the logging.properties to show output as per https://stackoverflow.com/questions/40690285/pivotal-server-log-messages-not-show-in-spring-tools-suite?rq=1

## Make this the default webapp (or change the context path)
Update in server.xml (removing what was in path)
            <Context docBase="jsf12" path="" reloadable="true" source="org.eclipse.jst.jee.server:jsf12"/></Host>
