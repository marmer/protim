[![Build Status](https://travis-ci.org/marmer/protim.svg)](https://travis-ci.org/marmer/protim)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=alert_status)](https://sonarcloud.io/dashboard?id=io.github.marmer.protim%3Aprotim)
[![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=coverage)](https://sonarcloud.io/component_measures?id=io.github.marmer.protim%3Aprotim&metric=Coverage)
[![Technical Dept](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=sqale_index)](https://sonarcloud.io/project/issues?facetMode=effort&id=io.github.marmer.protim%3Aprotim&resolved=false&types=CODE_SMELL)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=security_rating)](https://sonarcloud.io/component_measures?id=io.github.marmer.protim%3Aprotim&metric=Security)
[![Maintainability](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=sqale_rating)](https://sonarcloud.io/component_measures?id=io.github.marmer.protim%3Aprotim&metric=Maintainability)
[![Reliability](https://sonarcloud.io/api/project_badges/measure?project=io.github.marmer.protim%3Aprotim&metric=reliability_rating)](https://sonarcloud.io/component_measures?id=io.github.marmer.protim%3Aprotim&metric=Reliability)

# protim
The beginning of a small tool to manage production time


#TODO
* Add API documentation
* Secure the API
    * Basic and/or Digest Auth
    * JDBC Authentication
    * Consider to configure CSRF protection for the site
* Prepare desktop version
  * With remote connector (using the API)
  * With local storage
* Use BBD framework for end to end tests
* Fix Matchers Generator to be able to access non-public-methods