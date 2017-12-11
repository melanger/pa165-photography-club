# pa165-photography-club
Seminar project for PA165 at FI MUNI. The web application allows a photography club to manage their activities. Mainly, the managers of the club are interested in arranging photographic tours according to different themes (e.g. portraits, landscape, etc…). Each club member can decide to participate to one / more tours. Once a tour is completed, members have the possibility to post their comments and rate their experience. Each team member has a profile that other member can also see, with information such as name, gender, a photo, their address, age, and their experience about photography according to different categories. Also, they can add a statement about their reason to participate to the club. As very likely affected by Gear Acquisition Syndrome (GAS), it’s important for them to showcase all their equipment in the profile (cameras, lenses, filters, studio equipment, etc…).

More information can be found in the [project wiki](https://github.com/melanger/pa165-photography-club/wiki),
including the [Class diagram](https://github.com/melanger/pa165-photography-club/wiki/Class-diagram) and the [Use Case diagram](https://github.com/melanger/pa165-photography-club/wiki/Use-Case-Diagram).

## Usage
### Build
```
git clone https://github.com/melanger/pa165-photography-club.git
cd pa165-photography-club
mvn clean install
```
### Then run the web
```
cd pa165-photography-club-web
mvn tomcat7:run
```

## Team members
- Pavel Břoušek (433364) aka @melanger
- Denis Figula (410335) aka @DenisFigula
- Matúš Kravec (422169) aka @MatthewK286

There are only 3 members on this team (one dropped out).

## Project structure (maven submodules)
![maven submodules diagram](http://yuml.me/bd0c5c16.png)

## Simplified class diagram (omitting interfaces and implementations)
![simplified class diagram](http://yuml.me/da6f290b.png)
