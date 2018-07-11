[![Build Status](https://travis-ci.org/marmer/protim.svg)](https://travis-ci.org/marmer/protim)
[![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=coverage)](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=coverage)
[![Dependency Status](https://www.versioneye.com/user/projects/59d0915c6725bd445062a9f2/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/59d0915c6725bd445062a9f2)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=alert_status)](https://sonarcloud.io/dashboard?id=io.github.marmer.protim%3Aprotim)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=security_rating)](https://sonarcloud.io/project/issues?id=io.github.marmer.protim%3Aprotim&resolved=false&types=VULNERABILITY)
[![Technical Dept](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=sqale_index)](https://sonarcloud.io/project/issues?facetMode=effort&id=io.github.marmer.protim%3Aprotim&resolved=false&sinceLeakPeriod=true&types=CODE_SMELL)
[![Maintainability](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=sqale_rating)](https://sonarcloud.io/project/issues?id=io.github.marmer.protim%3Aprotim&resolved=false&types=CODE_SMELL://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=sqale_rating)
[![Reliability](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=reliability_rating)](https://sonarcloud.io/project/issues?id=io.github.marmer.protim%3Aprotim&resolved=false&types=BUG)

# protim
The beginning of a small tool to manage production time


#TODO
* Repackage by layer
* Add API documentation
* Secure the API
    * Basic and/or Digest Auth
    * JDBC Authentication
    * HTTPS
    * Consider to configure CSRF protection for the site
* Prepare desktop version
  * With remote connector (using the API)
  * With local storage
* Use BBD framework for end to end tests
* Fix Matchers Generator to be able to access non-public-methods