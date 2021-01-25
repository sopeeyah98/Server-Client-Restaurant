# Assessment
COMP 371 - Mobile Apps

Assumptions:
1. customer joins via server
2. customer does not add additional order after its first order
3. customer does not want to leave after sitting down
4. One robot per customer (could have been more efficient here)
5. 100 tables are enough in a restaurant
6. Once check is released, interaction is over.
7. Total price does not include tax or tip.

How I ran the code:
place all java files in a single folder

On 1 terminal:

javac Restaurant.java

java Restaurant 8585

// server open

On another terminal:

javac Client.java

java Client localhost 8585

// interact
