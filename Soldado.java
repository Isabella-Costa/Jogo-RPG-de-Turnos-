public class Soldado extends Personagem {
    //Sobrescrevendo Personagem//

    @Override 
    public void especial(){
        System.out.println("""
        BOOOOOOOOOOOOOOOOMM
            _ ._  _ , _ ._     
            (_ ' ( `  )_  .__)   
          ( (  (    )   `)  ) _) 
         (__ (_   (_ . _) _) ,__)
             `~~`\\ ' . /`~~`     
                  ;   ;          
                  /   \\          
        _________/_ __ \\_________
                """);}

    @Override
    public void desenhar(){
        System.out.println(""" 
              .---.
          ___/_____\\
        /\\.-`( '.')    
      /  /    \\-_-/_
      \\  `-.-"`'V'//-.
       `.__,    |//  , \\
            |L1//L1|\\ \\
           /---|[]==| / /
           \\__/ |   \\/\\/
            /_  |  L!_\\|
             |`^\"""^`|
             |    |   |
             |    |   |                    
             |    |   |                   
             |    |   |
             L____l___J
               |_ | -|
             (____|____)                   
        ^^^^^^^^^^^^^^^^^^^^
        """);
    }

    
        //Método único do Soldado//
    public void plantarBombas(){
        System.out.println("""
                                                             _ ._  _ , _ ._     
                                                           (_ ' ( `  )_  .__)   
                       O                                 ( (  (    )   `)  ) _) 
                       |\\                              (__ (_   (_ . _) _) ,__)
                       ||\\                                     `~~`\\ ' . /`~~`     
                       |    T                                       ;   ;          
                      /  \\  |                                        \\          
                    /    \\ [-]````````````````````````_________/_ __  \\_________""");
    }

        public void largarArma(){
            System.out.println("\nSoldado - Largando arma...");
        }
       
    
}
