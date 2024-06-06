package testes;
public abstract class Personagem {
    Arma_IF arma;

    public void setArma(Arma_IF arma) {
        this.arma = arma;
    }

    public void desenhar(){
        System.out.println("PERSONAGEM - Desenhando personagem");
    }

    public void correr(){
        System.out.println("""                                                           
                                                                                              ##########++                                  
                                                                                            ####      ####--                                
                                                                                          ####          ####                                
                                                                                          ####            ##                                
                                                                                          ####            ##                                
                                                                                          ####          @@##                                
                                                              ##############mm              ####        ####                                
                                                            ############################    ##############                                  
                                                        ######                      @@####      ######mm                                    
                                                      ######                            ####::                                              
                                                    ####++                                ######                  @@####..                  
                                                  ####        ++################            ######              ##########                  
                                                ####        ######    ::MM######                ####..      ######      ####                
                                                ####      ####MM          ####                    ######@@####@@        ####                
                                                  ##########            ####                        ########        ::####                  
                                                    ######            mm##mm              ####                    ######                    
                                                                      ####              ########              @@####--                      
                                                                    ####              ####----####          ######                          
                                                                  ####                ####      ####++  ######                              
                          ##########################            --##MM              ####          ##########                                
                            ++++++++++++++++++++++              ####              ####                                                      
                                                                ##              ####                                                        
                                                                ##--          --####                                                        
                  ##########################                    ####--        ####                                                          
                    @@@@@@@@@@@@@@@@@@@@@@                    ########..      ####++                                                        
                                                              ##@@  ####--      ####::                                                      
                                                            ####      ####--      ####--                                                    
                          ##########################      ####      ########--      ####..                                                  
                          --######################--    ####..    ####    ####--      ####                                                  
                                                        ####      ####      ####        ##++                                                
                                                      ####      ####        ####        ##--                                                
                                                    ####      ####          ##@@      ####                                                  
                                                  ####        ####        ####      ####                                                    
                                                ####        ####        mm##        ####                                                    
                                              ####        ####          ####      ####                                                      
                                          ::####        ####          ####      @@##                                                        
                                        ::####        ####            ##@@      ####                                                        
                                        ####      ..####            ####      ####                                                          
                                        ####    --####              ####    mm##..                                                          
                                        ####++@@####                ####    ####                                                            
                                          ########                    ########                                                              
                                                                                                                                                                                                                                                                                                                   
    """);  }

    public void arma(){
             arma.usarArma();
    }

    public void falar (){
        System.out.println("""
            |----------------------------------|        __                       
            |                                  |       | \\                       
            | "By the stars, it seems          |-------|  \\      ______         
            | our fate intertwines once        |       --- \\_____/**|_|_\\____  | 
            | more. The shadows grow           |         \\_______ --------- __&gt;-}
            | restless, whispering             |            /  \\_____|_____/   | 
            | secrets of ancient evils         |           *         |           
            | stirring in the depths.          |                    {O}         
            | But fear not, for where          |                                 
            | there is darkness, there         |                                 
            | too shall be light.              |                                 
            | Together, we shall carve         |                                 
            | our path through the             |                                 
            | abyss and emerge                 |                                 
            | victorious, for destiny          |                                 
            | favors the bold, and we          |                                 
            | are the architects of our        |                                 
            | own legend."                     |                                 
            |                                  |                                 
            |----------------------------------|                                          
        """);
    }
 
    public void especial(){
        System.out.println("PERSONAGEM - Poder Especial");
    }

    public void morrer (){
        System.out.println("""
            ,-=-.      
            /  +  \\     
            | ~~~ |     
            |R.I.P|     
       \\vV,,|_____|V,VV,,
                """);
    }

}
