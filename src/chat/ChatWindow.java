package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*
Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля, IP-адреса сервера,
порта подключения
к серверу, область ввода сообщений, JTextArea область просмотра сообщений чата и JButton подключения к серверу
и отправки сообщения в чат. Желательно сразу сгруппировать компоненты, относящиеся
к серверу сгруппировать на JPanel сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу
 */
public class ChatWindow extends JFrame{
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 100;
    private String message;
    private static String chatContent;

    // лепим панель логина




    JFrame frame = new JFrame("Easy Chat");



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


    public ChatWindow() {
        frame.setLayout(new GridLayout(9, 1));
        WindowListener winListener = new WinListener();
        frame.addWindowListener(winListener);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocation(WINDOW_POSX, WINDOW_POSY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelLogin.add(jLabelLogin);
        panelLogin.add(jFieldLogin);
        panelPassword.add(jLabelPassword);
        panelPassword.add(jFieldPassword);
        panelIp.add(jLabelIp);
        panelIp.add(jFieldIp);
        panelPort.add(jLabelPort);
        panelPort.add(jFieldPort);
        panelPort.add(jFieldPort);

        frame.add(panelLogin);
        frame.add(panelPassword);
        frame.add(panelIp);
        frame.add(panelPort);
        frame.add(btnLogin);

        textChatTextArea.setEditable(false);

        frame.add(scrollPane);
        frame.add(jLabelMessage);
        frame.add(jTextFieldMessage);
        frame.add(btnPushMsg);
        frame.setVisible(true);


        /**
         * Листенер загрузки содержимого чата из файла
         */
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream fr = new FileInputStream("src/chat/chat_content.txt");
                    int b;
                    while ((b = fr.read()) != -1) {
                        textChatTextArea.append(Character.valueOf((char) b).toString());
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /**
         * Листенер кнопки отправки сообщения
         */
        btnPushMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message = jFieldLogin.getText() + ": " + jTextFieldMessage.getText();
                textChatTextArea.append("\n" + message);
                chatContent = textChatTextArea.getText();
                System.out.println("Отправлено сообщение: " + jTextFieldMessage.getText());
                jTextFieldMessage.setText("");

            }
        });

        /**
         * Метод отправки сообщения по нажатию Enter
         */
        jTextFieldMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message = jFieldLogin.getText() + ": " + jTextFieldMessage.getText();
                textChatTextArea.append(message + "\n");
                chatContent = textChatTextArea.getText();
                jTextFieldMessage.setText("");
            }
        });

    }

    /**
     * Метод записи содержимого чата в файл
     * @param messages - записываемый текст
     * @param fileName - имя файла
     * @throws IOException - ошибка ввода-вывода
     */
    public static void writeChatToFile(String messages, String fileName) throws IOException {
        String chatText = textChatTextArea.getText();
        if (messages != null ) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(messages);
                writer.flush();
            }
        }

    }

    public static String getChatContent() {
        return chatContent;
    }

    /**
     * Листенер событий окна
     */
    public static class WinListener implements WindowListener {

        public void windowActivated(WindowEvent e) {  }

        public void windowClosed(WindowEvent e) {   }

        public void windowClosing(WindowEvent e) {
            try {
                writeChatToFile(chatContent, "src/chat/chat_content.txt");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        public void windowDeactivated(WindowEvent e) { }

        public void windowDeiconified(WindowEvent e) {  }

        public void windowIconified(WindowEvent e) {  }

        public void windowOpened(WindowEvent e) {  }
    }

}
