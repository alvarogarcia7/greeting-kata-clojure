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

(facts
  "fifth requirement: support greeting multiple people (commas and ands)"
  (fact
    "not shouting"
    (greet ["Person1" "Person2" "Person3"]) => "Hello, Person1, Person2, and Person3.")
  (fact
    "shouting"
    (greet ["PERSONA" "PERSONB" "PERSONC"]) => "HELLO, PERSONA, PERSONB, AND PERSONC."))


(facts
  "sixth requirement: mixing formal and informal greeting style"
  (fact
    "formal is first, no matter the order of the names"
    (greet ["Personb" "persona" "PERSONA" "PERSONB"]) => "Hello, Personb and persona. AND HELLO PERSONA AND PERSONB!"
    (greet ["PERSONA" "persona" "Personb" "PERSONB"]) => "Hello, persona and Personb. AND HELLO PERSONA AND PERSONB!"
    )
  (fact
    "greet one formally and one informally"
    (greet ["Personb" "PERSONA"]) => "Hello, Personb. AND HELLO PERSONA!"
    )
  (fact
    "greet several formally and one informally"
    (greet ["Personb" "PersonC" "PERSONA"]) => "Hello, Personb and PersonC. AND HELLO PERSONA!"
    )
  (fact
    "greet one formally and several informally"
    (greet ["Personb" "PERSONC" "PERSONA"]) => "Hello, Personb. AND HELLO PERSONC AND PERSONA!"
    (greet ["Personb" "PERSONC" "PERSONA" "PERSOND"]) => "Hello, Personb. AND HELLO PERSONC, PERSONA, AND PERSOND!"
    ))
