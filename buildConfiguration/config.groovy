// File: config.groovy
database {
    server = 'localhost'
    name = 'twitter4x'
    port = '3306'
}

environments {
    development {
        database {
            user = "root"
            password = "1221"
        }
    }

    test {
        database {
            name = 'twitter4x-test'
            user = "root"
            password = "1221"
        }
    }
}
