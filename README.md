# Simulated Annealing Playfair Cipher

## 4th year undergraduate java project for Artificial Intelligence module

The field of cryptanalysis is concerned with the study of ciphers, having as its objective the
identification of weaknesses within a cryptographic system that may be exploited to convert
encrypted data (cipher-text) into unencrypted data (plain-text). Whether using symmetric or
asymmetric techniques, cryptanalysis assumes no knowledge of the correct cryptographic key
or even the cryptographic algorithm being used.

Assuming that the cryptographic algorithm is known, a common approach for breaking a cipher
is to generate a large number of keys, decrypt a cipher-text with each key and then examine the
resultant plain-text. If the text looks similar to English, then the chances are that the key is a
good one. The similarity of a given piece of text to English can be computed by breaking the
text into fixed-length substrings, called n-grams, and then comparing each substring to an
existing map of n-grams and their frequency. This process does not guarantee that the outputted
answer will be the correct plain-text, but can give a good approximation that may well be the
right answer.

## Simulated Annealing
Simulated annealing (SA) is an excellent approach for breaking a cipher using a randomly
generated key. Unlike conventional Hill Climbing algorithms, that are easily side-tracked by
local optima, SA uses randomization to avoid heuristic plateaux and attempt to find a global
maxima solution.

## How the playfair cypher works

The Playfair cipher uses a 5 by 5 table containing a key word or phrase. Memorization of the keyword and 4 simple rules was all that was required to create the 5 by 5 table and use the cipher.

To generate the key table, one would first fill in the spaces in the table with the letters of the keyword (dropping any duplicate letters), then fill the remaining spaces with the rest of the letters of the alphabet in order (usually omitting "J" or "Q" to reduce the alphabet to fit; other versions put both "I" and "J" in the same space). The key can be written in the top rows of the table, from left to right, or in some other pattern, such as a spiral beginning in the upper-left-hand corner and ending in the center. The keyword together with the conventions for filling in the 5 by 5 table constitute the cipher key.

To encrypt a message, one would break the message into digrams (groups of 2 letters) such that, for example, "HelloWorld" becomes "HE LL OW OR LD". These digrams will be substituted using the key table. Since encryption requires pairs of letters, messages with an odd number of characters usually append an uncommon letter, such as "X", to complete the final digram. The two letters of the digram are considered opposite corners of a rectangle in the key table. To perform the substitution, apply the following 4 rules, in order, to each pair of letters in the plaintext:

If both letters are the same (or only one letter is left), add an "X" after the first letter. Encrypt the new pair and continue. Some variants of Playfair use "Q" instead of "X", but any letter, itself uncommon as a repeated pair, will do.

If the letters appear on the same row of your table, replace them with the letters to their immediate right respectively (wrapping around to the left side of the row if a letter in the original pair was on the right side of the row).

If the letters appear on the same column of your table, replace them with the letters immediately below respectively (wrapping around to the top side of the column if a letter in the original pair was on the bottom side of the column).

If the letters are not on the same row or column, replace them with the letters on the same row respectively but at the other pair of corners of the rectangle defined by the original pair. The order is important – the first letter of the encrypted pair is the one that lies on the same row as the first letter of the plaintext pair.

To decrypt, use the INVERSE (opposite) of the last 3 rules, and the 1st as-is (dropping any extra "X"s, or "Q"s that do not make sense in the final message when finished).

#### Rule 1: Diagraph Letters in Different Rows and Columns 
Create a “box” inside the matrix with each diagraph letter as a corner and read off the letter at the opposite corner of the same row, e.g. AR→SI. Reverse the process to decrypt a cypher-text diagraph.

#### Rule 2: Diagraph Letters in Same Row
Replace any letters that appear on the same row with the letters to their immediate right, wrapping around the matrix if necessary. Decrypt by replacing cipher-text letters the with letters on their immediate left.

#### Rule 3: Diagraph Letters in Same Column 
Replace any letters that appear on the same column with the letters immediately below, wrapping back around the top of the column if necessary. Decrypt by replacing ciphertext letters the with letters immediately above.

## Key Shuffing
1. Swap single letters (90%)
2. Swap random rows (2%)
3. Swap columns (2%)
4. Flip all rows (2%)
5. Flip all columns (2%)
6. Reverse the whole key (2%)

References:
1. [Wiki](https://en.wikipedia.org/wiki/Playfair_cipher)
