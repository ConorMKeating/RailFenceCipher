# RailFenceCipher
A user customisable rail fence cypher. Accepts files or URL's and generates an encrypted output file. Final project for OOP module.

This application was designed in such a way as to force the user to follow a certain path to encryption and decryption.
Originally it presented the same menu as shown in the assignment description but I found it clunky by having to go through
three menu otions to encrypt or decrypt. So, I changed the layout. The program was built from the foundations laid down
in the Teams workshop, and from there, I have adapted it to function as I wanted it too.

The program runs as follows:<br>
1 User selects whether to encrypt/decrypt a file, or encrypt a url. (Just type in one filename alone e.g. Dante.txt )<br>
2 User is asked to enter the key (number of rows) and offset (starting row).<br>
3 For files, user is asked whether to encrypt or decrypt. For urls the encryption is already done in step 2.<br>
4 For files, encryption or decryption occurs.<br>
<br>
To run this application follow these steps:<br>
1 Download the src folder which contains the uncompiled .java file.<br>
2 Open a command prompt at src/ie/gmit/dip folder.<br>
3 Enter the following command..   javac *.java<br>
4 Navigate back to the src folder in command prompt.<br>
5 Enter the following command..   java ie.gmit.dip.Runner<br>
6 Program will run and complete in command prompt window.<br>
