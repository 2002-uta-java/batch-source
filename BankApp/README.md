CLI Bank App that uses a database for persistance.

Implemented using the Java Beans Pattern, the DAO pattern and JDBC.

For user of the bank:
	The username has to be unique
	The user ID will be generated in the back end automatically
	The password is not nullable, and is encrypted using sha256 then sha1
	
For account:
	The account is created automatically whenever a new user is created and the user ID is used as a foreign key for the account
	The account ID is generated in the back end automatically
	balance starts at zero, using numeric(20,2), meaning balance cannot execed 20 digits
	
Program runs in an infinite loop:
	you can sign-in given you have a correct username and password
	you can sign-up given you have a unique username that hasn't been taken and a password
	you can sign-out given you're signed in already, this will clear any object that was used in the previous transaction
	
To Kill Program use CTRL + C