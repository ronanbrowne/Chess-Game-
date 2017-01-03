import java.util.*;
// next are all libraries needed for the add ons for code given to us
//well truth is was getting error saying something not been seen, think was due to array
//so imported everything, problem resolved & has no effect on logic/game time etc
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Stack;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class AIAgent{
  Random rand;


  public AIAgent(){
    rand = new Random();

  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/



// this sets up method for deciding which  of the choices user will play
//really it's option what code is called between next move & random move
  public Move MethodPicked(int picked,Stack possibilities){
    Move selectedMove = new Move();
    if(picked==0){
      selectedMove = randomMove(possibilities);
      return selectedMove;
    }
    else{
      selectedMove = nextBestMove(possibilities);
      return selectedMove;
    }
  }
//code given already
  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  public Move nextBestMove(Stack possibilities){
	  //added code
    int HighestPoints = -1;//set as minus
    Move selectedMove= null;// so null is better option
    ArrayList<Move> random = new ArrayList<Move>();//initialises arraylist
    Move move = null;//invokes move, otherwise wouldn't move


    System.out.println("Agent picked the best piece to take");


    for(int i=1;i < possibilities.size();i++){ //loop for white moves
       move = (Move) possibilities.pop();//selects top move of stack, removes it
       random.add(move);//adds to array list moves
    }
    Collections.shuffle(random);//shuffels the arraylist

        for(int i=0; i<random.size();i++){//iterates through the stack
          move = random.get(i);
          int value = move.getPoints();
          if(value>HighestPoints){
            selectedMove = move;
          }// close for loop, will select bottom option
        }//as stack already jumbled doesn't matter


  return selectedMove;
}
  //method for two deep level moves
  public Move twoLevelsDeep(Stack whitePossibilities, Stack blackPoints){
    System.out.println("TWO LEVELS DEEP");

    int HighestPoints = -1100;//lower than lowest score in case the only option is to move into poor position
    int value, value2;
    Move selectedMove= null;
    Move move= null;

    ArrayList<Move> WM2 = new ArrayList<Move>();


    while(!whitePossibilities.empty()){//  Highest value.
		      move = (Move)whitePossibilities.pop();//removes white first move
		      value = (int)blackPoints.pop(); //points black move
	      value2 = move.getPoints(); //points white move

      //Setting points.
	  	 move.setPoints((move.getPoints()-value));




      if((move.getPoints())>HighestPoints){//gives base score for white move
	  	HighestPoints = move.getPoints();//to make a decision to choose highest score move
	   }
      //if it can take a piece then loose a piece (and pieces are equal) this move has more importance
      //as it will loose the points in the next turn otherwise.
      if(value2 == value){
        move.setPoints((move.getPoints()+value2));
        //reset highest points
        if((move.getPoints())>HighestPoints){
          HighestPoints = move.getPoints();
        }
      }

      WM2.add(move); //adds the move to a arraylist.
    }

    int moveID = rand.nextInt(WM2.size()); //loop to randomly select HighestPoints
		System.out.println("HighestPoints: "+HighestPoints);
		 Collections.shuffle(WM2);//shuffels the arraylist


    for(int i=0;i <WM2.size();i++){//loops through array 

		      move = (Move)WM2.get(i); //selects move
		      System.out.println("ValuePoints: "+move.getPoints());//returns value 

		      if(move.getPoints()>=HighestPoints){ //move equal or points more than
		        selectedMove = move;//chooses highest move value
		      }
	    }


  return selectedMove;
  }
}
