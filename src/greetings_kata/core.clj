(ns greetings-kata.core
  (:gen-class))

(defn
  greet
  [name]
  (let [value (if (nil? name) "my friend" name)]
    (str "Hello, " value ".")))


