public class Human extends Player {

        private double balance;
        
        public Human(double balance, Card cardDrawn1, Card cardDrawn2) {
                super(cardDrawn1, cardDrawn2);
                this.balance = balance;
        }

}