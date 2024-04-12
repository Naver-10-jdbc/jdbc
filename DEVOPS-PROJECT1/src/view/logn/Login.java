package view.logn;

import db.LoginDAO;
import view.main.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField txt_id;
    private JPasswordField txt_pw;
    private String userId; // 사용자가 입력한 아이디를 저장할 변수



    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 568);
        setLocationRelativeTo(null);
        setResizable(false); //화면 크기 조정 못하게
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel txt_login = new JLabel("로그인");
        txt_login.setFont(new Font("굴림", Font.PLAIN, 26));
        txt_login.setBounds(303, 87, 79, 63);
        contentPane.add(txt_login);

        JLabel label_id = new JLabel("아이디");
        label_id.setLabelFor(label_id);
        label_id.setFont(new Font("굴림", Font.PLAIN, 18));
        label_id.setBounds(75, 196, 412, 42);
        contentPane.add(label_id);

        txt_id = new JTextField();
        txt_id.setBounds(173, 198, 314, 42);
        contentPane.add(txt_id);
        txt_id.setColumns(10);

        JLabel label_pw = new JLabel("비밀번호");
        label_pw.setFont(new Font("굴림", Font.PLAIN, 18));
        label_pw.setBounds(75, 262, 412, 42);
        contentPane.add(label_pw);

        txt_pw = new JPasswordField();
        txt_pw.setBounds(173, 264, 314, 42);
        contentPane.add(txt_pw);

        JButton btn_sign = new JButton("회원가입");
        btn_sign.setFont(new Font("굴림", Font.PLAIN, 25));
        btn_sign.setBounds(255, 393, 170, 42);
        contentPane.add(btn_sign);
        btn_sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Sign();
            }
        });


        JButton btn_login = new JButton("\uB85C\uADF8\uC778");
        btn_login.setFont(new Font("굴림", Font.BOLD, 23));
        btn_login.setBounds(522, 196, 121, 108);
        contentPane.add(btn_login);

        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 사용자가 입력한 아이디를 인스턴스 변수에 저장
                userId = txt_id.getText();
                String inputPassword = new String(txt_pw.getPassword());

                // 로그인 DAO를 통해 로그인 처리
                LoginDAO loginDAO = new LoginDAO();
                if (loginDAO.login(userId, inputPassword)) {
                    // 로그인 성공 시 사용자 아이디를 Session 클래스에 저장
                    Session.getInstance().setUserId(userId);
                    // 로그인 성공 시 메인 화면으로 이동
                    new Main();
                    // 로그인 창 닫기
                    dispose();
                } else {
                    // 로그인 실패 시 메시지 출력
                    JOptionPane.showMessageDialog(Login.this, "아이디 또는 비밀번호가 올바르지 않습니다.");
                }
            }
        });

        setVisible(true);
    }
}
