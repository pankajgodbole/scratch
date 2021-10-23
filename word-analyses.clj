(ns scratch.word-analysis
  (:require [clojure.string :as cs]))

(def pfo-book-text
  ""
  (slurp "https://www.gutenberg.org/files/2701/2701-0.txt"))

(def pfo-book-words
  "Regex to match all words"
  (re-seq #"[\w|']+" pfo-book-text))

(->> pfo-book-words
     (cs/lower-case)
     (frequencies ))

(->> pfo-book-words
      (map cs/lower-case)
      (frequencies ))

(->> pfo-book-words
     (map cs/lower-case)
     (frequencies)
     (take 20))

(->> pfo-book-words
     (map cs/lower-case)
     (frequencies)
     (map val)
     (take 9))

(->> pfo-book-words
     (map cs/lower-case)
     (frequencies)
     (sort-by val)
     (take 9))

(->> pfo-book-words
     (map cs/lower-case)
     (frequencies)
     (sort-by val)
     (reverse)
     (take 9))

(->> pfo-book-words
     (map cs/lower-case)
     (frequencies)
     (sort-by val)
     (take-last 9))

(->> pfo-book-words
     (map cs/lower-case)
     (frequencies)
     (sort-by val)
     (reverse)
     (take 9))


;;
;; Fetch the 100 most common words in the English language
;;
(def common-words
  ""
  (-> (slurp "/home/p/Documents/most-common-english-words-100.txt")
    (cs/split #" ")
    set ))

(count common-words)

(->> pfo-book-words
     (map cs/lower-case)
     (remove common-words)
     (frequencies)
     (sort-by val)
     (reverse)
     (take 9))

(->> pfo-book-words
     (map cs/lower-case)
     (remove common-words)
     (frequencies)
     (sort-by val)
     (reverse)
     (take 9))

(->> pfo-book-words
     (map cs/lower-case)
     (remove common-words)
     (frequencies)
     (sort-by val)
     (reverse)
     (take 19))

(->> pfo-book-words
     (map cs/lower-case)
     (remove common-words)
     (frequencies)
     (sort-by val)
     (reverse)
     (take 29))

(common-words "is")
(common-words "his")

(def pfo-distinct-book-words
  ""
  (->> pfo-book-words
    (distinct)))

(->> pfo-distinct-book-words
    (group-by count)
    (count))

(->> pfo-distinct-book-words
     (sort-by count)
     (reverse))

(->> pfo-distinct-book-words
    (sort-by count)
    (take-last 9)
    (group-by count)
    (spit "/home/p/Clojure/Examples/scratch/out/test.out"))

;;
;; Display words longest to shortest
;;
(->> pfo-distinct-book-words
     ;;(sort-by count)
     ;;(reverse)
     ;;(take 20)
     (group-by count)
     (sort-by first)
     (reverse)
     ;;(count)
     )

;;
;; Count the number of words that are 17-letters long (10)
;;
(->> pfo-distinct-book-words
     (group-by count)
     (sort-by first)
     (reverse)
     (rest)
     (second)
     (second)
     ;;(first)
     (count)
     )


;;
;; Longest palindrome
;;

(def racecar-palindrome
  "racecar")

(concat (reverse racecar-palindrome))

(defn palindrome?
  "Predicate function to check whether any collection [coll] is a palindrome"
  [coll]
  (= (seq coll) (reverse coll)))

(palindrome? racecar-palindrome)

(->> pfo-distinct-book-words
     (first)
     (palindrome?))

(defn longest-palidrome
  "Takes a collection of words [ws], and finds the longest palindrome in ws"
  [ws]
  (->> ws
     (filter palindrome?)
     (sort-by count)
     (reverse)
     (first)
     ;;(count)
     ))

(longest-palidrome pfo-distinct-book-words)
