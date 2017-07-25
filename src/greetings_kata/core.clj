(ns greetings-kata.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn
  greet
  [name]
  (letfn [(upper-case? [line] (and (> (count line) 0) (every? #(Character/isUpperCase %) line)))
          (anonymous? [name] (nil? name))]
    (let [greeting "Hello, "
          value (if (anonymous? name) "my friend" name)
          greeting (if (upper-case? name) (str/upper-case greeting) greeting)]
      (str greeting value "."))))


