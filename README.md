# pa165-photography-club
Seminar project for PA165 at FI MUNI. The web application allows a photography club to manage their activities. Mainly, the managers of the club are interested in arranging photographic tours according to different themes (e.g. portraits, landscape, etc…). Each club member can decide to participate to one / more tours. Once a tour is completed, members have the possibility to post their comments and rate their experience. Each team member has a profile that other member can also see, with information such as name, gender, a photo, their address, age, and their experience about photography according to different categories. Also, they can add a statement about their reason to participate to the club. As very likely affected by Gear Acquisition Syndrome (GAS), it’s important for them to showcase all their equipment in the profile (cameras, lenses, filters, studio equipment, etc…).

More information can be found in the [project wiki](https://github.com/melanger/pa165-photography-club/wiki),
including the [Entity class diagram](https://github.com/melanger/pa165-photography-club/wiki/Entity-class-diagram),
the [Use Case diagram](https://github.com/melanger/pa165-photography-club/wiki/Use-Case-Diagram)
and the [Layers class diagram](https://github.com/melanger/pa165-photography-club/wiki/Layers-class-diagram).

## Usage
### Build
```
git clone https://github.com/melanger/pa165-photography-club.git
cd pa165-photography-club
mvn clean install
```
### Then run the web…
```
cd pa165-photography-club-web
mvn tomcat7:run
```
### … or run the REST API only
```
cd pa165-photography-club-rest
mvn tomcat7:run
```
You can then access the web at http://localhost:8080/pa165/ or test the REST API using [cURL commands](https://github.com/melanger/pa165-photography-club/blob/master/cURL.md).
You can also check out the API documentation at [pa165photographyclubrestapi.docs.apiary.io](https://pa165photographyclubrestapi.docs.apiary.io/) or view the API blueprint [apiary.apib](https://github.com/melanger/pa165-photography-club/blob/master/apiary.apib).

Credentials for logging in:
- regular user (member)
  - ben@mail.com
  - BenisBen
- super user (manager)
  - anton@mail
  - AntonIsBest

If you are not logged in, you can see public member profiles and tours.
If you are logged in as a regular member, you can also add new equipment to your profile.
If you are logged in as a manager, you can also manage tours (create and delete them).

## Team members
- Pavel Břoušek (433364) aka @melanger
- Denis Figula (410335) aka @DenisFigula
- Matúš Kravec (422169) aka @MatthewK286

There are only 3 members on this team (one dropped out).

## Project structure (maven submodules)
![maven submodules diagram](http://yuml.me/1eb17889.png)

