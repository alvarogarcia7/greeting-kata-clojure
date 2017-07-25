(ns greetings-kata.core-test
  (:use [midje.sweet])
  (:require [greetings-kata.core :refer :all]))

(facts
  "about the first requirement: interpolate a simple name"
  (fact
    ""
    (greet "bob") => "Hello, bob"))

