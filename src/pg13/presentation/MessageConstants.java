package pg13.presentation;

public class MessageConstants
{
	
	//Welcome Message/Header
	public static final String LOGON 					= 			"Logged in as ";
	public static final String WELCOME_HEADER 			= 			"Welcome to Puzzle Games 2013 (PG13)";
	public static final String INSTRUCTIONS_LOGGED_OUT 	= 			"Begin your PG13 experience by logging in.  You may choose to login as a guest.\n\n";																		
	public static final String INSTRUCTIONS_LOGGED_IN	=			"Select the Create option above to begin creating a new cryptogram.  " +
																		"You can preview and test your cryptogram in the preview area therein.\n\n" +
																		"Once your cryptogram is complete and saved, you can find it among other cryptograms " +
																		"by selecting the Play option.";

	//Logon/Sign Up Screen Message
	public static final String LOGIN_INFO 				= 			"Select your username from the list below. If you do not have a username you may click cancel and go sign up or select Guest from the drop down.";
	public static final String SIGNUP_INFO 				= 			"Create a Username with 1 - 15 characters.  Valid characters include A-Z, a-z, 0-9.  No spaces are allowed.";
	public static final String INVALID_USERNAME 		= 			"That username is already taken or is invalid.";
	public static final String LOGON_SUCCESS 			= 			"Successfully Created a user";
	
	//Button Text
	public static final String SAVE_PUZZLE 				= 			"Save this Puzzle";
	public static final String GENERATE_PREVIEW 		= 			"Generate Preview";
	public static final String CHECK_SOLUTION 			= 			"Check Solution";
	public static final String SOLVE_SELECTED 			= 			"Play Selected Puzzle";
	
	//Puzzle Completion Messages
	public static final String PUZZLE_SOLVED 			= 			"Puzzle solved!";
	public static final String PUZZLE_UNSOLVED			= 			"Puzzle NOT solved!";	
	
	//Puzzle Saving Messages
	public static final String SAVE_SUCCESS				=			"Save Puzzle";
	public static final String SAVE_SUCCESS_MSG			=			"Puzzle saved!";
	
	//Error/Invalid Data Messages
	public static final String INVALID_TEXT 			= 			"Invalid text";
	public static final String INVALID_TEXT_MESSAGE	 	= 			"The text you have entered is invalid. Please modify the text.";
	public static final String SAVE_ERROR				=			"Save Puzzle Error";

}
