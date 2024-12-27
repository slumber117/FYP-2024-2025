public class main{

    public void main (String[] args){
        SwingUtilities.invokeLater(() -> {
            Creator create = new Creator("New Page");
            create.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            create.setVisible(true);
        })
    }
}
