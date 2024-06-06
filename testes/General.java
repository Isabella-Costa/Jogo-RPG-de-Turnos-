package testes;

public class General extends Soldado {

    //Sobrescrevendo Personagem//
    @Override
    public void especial(){
        System.out.println("""
                                             |                                  
                                 |                                  
                                 |                                  
                                _|_                                 
                               /___\\                                
                              /_____\\                               
                             /oo   oo\\                              
 \\___________________________\\       /___________________________/  
  `-----------|------|--------\\_____/--------|------|-----------'   
             ( )    ( )     O|OOo|oOO|O     ( )    ( )              
                                                                 
    """);
    }
    
    @Override
    public void desenhar(){
        System.out.print("""                                                                                         
                                         
          

                                                            
                                                            
            """);}

    
    //Método único do General
    public void tanqueDeGuerra(){
        System.out.println("""
                Art by Brian Green                 
          ________                 
      (( /========\\                
      __/__________\\____________n _
  (( /              \\_____________]
    /  =(*)=          \\            
    |_._._._._._._._._.|         ! 
(( / __________________ \\      =o 
  | OOOOOOOOOOOOOOOOOOO0 |   =     
                """);
    }
}
