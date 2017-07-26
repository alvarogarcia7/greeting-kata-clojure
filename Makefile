.PHONY: test
test:
	lein midje

.PHONY: test-watch
test-watch:
	lein midje :autotest

