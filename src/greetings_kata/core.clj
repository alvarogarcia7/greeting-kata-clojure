(ns greetings-kata.core
  (:gen-class))

(defn
  greet
  [name]
  (let [greeting "Hello, "
        value (if (nil? name) "my friend" name)
        upper-case? (and (> (count name) 0) (every? #(Character/isUpperCase %) name))
        greeting (if upper-case? (clojure.string/upper-case greeting) greeting)]
    (str greeting value ".")))


