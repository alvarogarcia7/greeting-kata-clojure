(ns greetings-kata.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn
  greet
  [name]
  (letfn [(upper-case? [line] (and (> (count line) 0) (every? #(Character/isUpperCase %) line)))
          (anonymous? [name] (nil? name))
          (anonymous-greeting [_] "Hello, my friend.")]
    (let [
          greeting (if (anonymous? name) anonymous-greeting (fn [name] (str "Hello, " name ".")))
          greeting (if (upper-case? name) (comp str/upper-case greeting) greeting)]
      (greeting name))))


