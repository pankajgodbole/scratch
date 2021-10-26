(ns scratch.java-interop)

(let [point   (java.awt.Point. 1 2)]
  (set! (. point -x) (inc (. point -x)))
  (set! (. point -y) (dec (. point -y)))
  [(. point -x) (. point -y)])