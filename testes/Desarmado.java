package testes;
public class Desarmado implements Arma_IF{

    @Override
    public void usarArma() {
        System.out.println("""                           
                  ##########                      
            ####::::::::::::::######              
          ##::::::::::::::::::::::::::####        
        ++::::                ::::::::::::##      
      ##::::      ##      ##      ::::##..        
    ##::::##++    ##        ##      ::##          
  ##::::  ##      ##    ##    ##@@@@########mmmmmm
  ##::--  ##      ##    ##                        
  mm::    ##########    ##                        
  ::::  ##        ##  ####                        
##::::  ##          ##  ::##                      
##::::    ####  ######                            
  ::::  ##                                        
  ##::  ##                                        
  ##::::  ############          mm################
  ##::::  ##          ##      mm##  ::::####      
    ##::::##          ::  ::##    --::##          
      ##::::############        ::::::####        
        ##::::--              ::::::::::::##      
          ##::::::::::::::::::::::::++####        
            ####::::::::::::++######              
                  ########                        
                                                                                                                                                                                  
                """);
    }
    
}