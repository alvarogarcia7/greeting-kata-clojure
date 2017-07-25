(ns greetings-kata.core-test
  (:use [midje.sweet])
  (:require [greetings-kata.core :refer :all]))

(facts
  "about the first requirement: interpolate a simple name"
  (fact
    ""
    (greet "bob") => "Hello, bob."))

(facts
  "second requirement: a default value"
  (fact
    ""
    (greet nil) => "Hello, my friend."))

(facts
  "third requirement: support shouting"
  (fact
    ""
    (greet "JOHN") => "HELLO, JOHN."))

(facts
  "fourth requirement: support greeting multiple people"
  (fact
    "not shouting"
    (greet ["Person1" "Person2"]) => "Hello, Person1 and Person2.")
  (fact
    "shouting"
    (greet ["PERSONA" "PERSONB"]) => "HELLO, PERSONA AND PERSONB."))
