# Coroutines and @Transactional issue

@Transactional doesn't seem to work when using Coroutines.

## Set up

### Start Postgres

```bash
$ docker compose up
```

### Start Micronaut

```bash
$ ./gradlew run
```

# Steps to reproduce

Run the failing test:
```bash
./gradlew test -i
```

## Alternatively, reproduce the error manually with the following steps

Send a post request to `/suspend`:

```bash
$ curl -X POST -d '{"id":"C3076DE3-4678-4B6A-A977-15F35607B7C7"}' http://localhost:8080/suspend -s -S -v -H "Content-Type: ap
plication/json"
```

**Expected:** Duplicate key value exception occurs, and no rows should be created in the database.

**Actual:** One row is still created in the database.
