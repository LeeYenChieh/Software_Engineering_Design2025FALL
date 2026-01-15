public class adapter {
    // 舊系統期待的介面
    interface Duck {
        void quack();
    }

    // 新系統期待的介面
    interface Turkey {
        void gobble();
    }

    class TwoWayAdapter implements Duck, Turkey {
        Duck duck;
        Turkey turkey;

        public TwoWayAdapter(Turkey turkey) {
            this.turkey = turkey;
        }

        @Override
        public void quack() {
            turkey.gobble();
        }

        @Override
        public void gobble() {
            turkey.gobble();
        }
    }

    public class Main {
        public static void main(String[] args) {
            WildTurkey turkey = new WildTurkey();
            TwoWayAdapter adapter = new TwoWayAdapter(turkey);

            System.out.println("--- 在舊系統（預期鴨子）中使用 ---");
            testDuck(adapter); 

            System.out.println("\n--- 在新系統（預期火雞）中使用 ---");
            testTurkey(adapter);
        }

        static void testDuck(Duck duck) {
            duck.quack();
        }

        static void testTurkey(Turkey turkey) {
            turkey.gobble();
        }
    }
}



