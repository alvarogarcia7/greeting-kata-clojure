(ns greetings-kata.core
  (:gen-class))

(defn
  greet
  [name]
  (let [greeting "Hello, "
        value (if (nil? name) "my friend" name)
        upper-case? (fn [line] (and (> (count line) 0) (every? #(Character/isUpperCase %) line)))
        greeting (if (upper-case? name) (clojure.string/upper-case greeting) greeting)]
    (str greeting value ".")))


