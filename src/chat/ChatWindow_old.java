package chat;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;

/*
Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля, IP-адреса сервера,
порта подключения
к серверу, область ввода сообщений, JTextArea область просмотра сообщений чата и JButton подключения к серверу
и отправки сообщения в чат. Желательно сразу сгруппировать компоненты, относящиеся
к серверу сгруппировать на JPanel сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу
 */
public class ChatWindow_old extends JFrame {
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 100;
    private String message;

    // лепим панель логина
    JPanel panelLogin = new JPanel(new GridLayout(1, 2));
    JLabel jLabelLogin = new JLabel("Введите логин: ");
    JTextField jFieldLogin = new JTextField();

    JPanel panelPassword = new JPanel(new GridLayout(1, 2));
    JLabel jLabelPassword = new JLabel("Введите пароль: ");
    JTextField jFieldPassword = new JTextField();

    JPanel panelIp = new JPanel(new GridLayout(1, 2));
    JLabel jLabelIp = new JLabel("Введите IP адрес сервера: ");
    JTextField jFieldIp = new JTextField();

    JPanel panelPort = new JPanel(new GridLayout(1, 2));
    JLabel jLabelPort = new JLabel("Введите номер порта: ");
    JTextField jFieldPort = new JTextField();

    JButton btnLogin = new JButton("Подключиться");
    static JTextArea textChatTextArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textChatTextArea);
    JPanel panelMain = new JPanel(new GridLayout(10, 1));

    JLabel jLabelMessage = new JLabel("Введите ваше сообщение: ");
    JTextField jTextFieldMessage = new JTextField();
    JButton btnPushMsg = new JButton("Отправить сообщение");
//    String logChat = "";
//    char[] bufferLog;

    public ChatWindow_old() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat Window");
        setResizable(false);
        setVisible(true);

        setLayout(new GridLayout(1, 1));
        panelLogin.add(jLabelLogin);
        panelLogin.add(jFieldLogin);
        panelPassword.add(jLabelPassword);
        panelPassword.add(jFieldPassword);
        panelIp.add(jLabelIp);
        panelIp.add(jFieldIp);
        panelPort.add(jLabelPort);
        panelPort.add(jFieldPort);
        panelMain.add(panelLogin);
        panelMain.add(panelPassword);
        panelMain.add(panelIp);
        panelMain.add(panelPort);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream fr = new FileInputStream("src/chat/chat.txt");
                    int b;
                    while ((b = fr.read()) != -1) {
                        textChatTextArea.append(Character.valueOf((char) b).toString());
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnPushMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message = jFieldLogin.getText() + ": " + jTextFieldMessage.getText();
                textChatTextArea.append("\n" + message);
                System.out.println("Отправлено сообщение: " + jTextFieldMessage.getText());
                writeChatToFile(jTextFieldMessage.getText(), "src/chat/chat.txt");
                jTextFieldMessage.setText("");

            }
        });

        jTextFieldMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message = jFieldLogin.getText() + ": " + jTextFieldMessage.getText();
                textChatTextArea.append("\n" + message);
                System.out.println("Отправлено сообщение: " + jTextFieldMessage.getText());
                writeChatToFile(jTextFieldMessage.getText(), "src/chat/chat.txt");
                jTextFieldMessage.setText("");
            }
        });


//        setLayout(new GridLayout(2, 2));
        panelMain.add(btnLogin);
        textChatTextArea.setEditable(false);

        panelMain.add(scrollPane);

        panelMain.add(jLabelMessage);
        panelMain.add(jTextFieldMessage);
        panelMain.add(btnPushMsg);
        add(panelMain);
        setVisible(true);

//        ChatWindow.writeChatToFile("src/chat/chat_log.txt");
    }

    public static void writeChatToFile(String message, String fileName) {
//        System.out.println("вошли----------------------");
        String chatText = textChatTextArea.getText();
        System.out.println("Внести: " + message);
    }

    public void chatInit() {

    }
}
