setup:
	make -C app setup

clean:
	make -C app clean

build:
	make -C app build

start:
	make -C app start

start-dist:
	make -C app start-dist

generate-migrations:
	make -C app generate-migrations

lint:
	make -C app lint

test:
	make -C app test

report:
	cd app && ./gradlew jacocoTestReport

check-updates:
	make -C app check-updates

.PHONY: build