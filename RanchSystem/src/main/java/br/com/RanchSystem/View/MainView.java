package br.com.RanchSystem.View;

import br.com.RanchSystem.Controller.FuncionarioController;
import br.com.RanchSystem.Controller.GadoController;
import br.com.RanchSystem.Controller.PlantacaoController;
import br.com.RanchSystem.Controller.DespesaController;
import br.com.RanchSystem.Entity.Funcionario;
import br.com.RanchSystem.Entity.Gado;
import br.com.RanchSystem.Entity.Plantacao;
import br.com.RanchSystem.Entity.Despesa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainView extends JFrame {

    private JTabbedPane tabbedPane;
    private FuncionarioController funcionarioController;
    private GadoController gadoController;
    private PlantacaoController plantacaoController;
    private DespesaController despesaController;

    private JLabel lblTotalGados, lblTotalFuncionarios, lblTotalPlantacoes;
    private JLabel lblHectaresTotal, lblMachos, lblFemeas, lblSaldoFinanceiro;

    private final Color PRIMARY_COLOR = new Color(45, 52, 54);
    private final Color ACCENT_COLOR = new Color(9, 132, 227);
    private final Color BG_COLOR = new Color(240, 242, 245);
    private final Color SUCCESS_COLOR = new Color(0, 184, 148);
    private final Color DANGER_COLOR = new Color(225, 112, 85);

    public MainView() {
        funcionarioController = new FuncionarioController();
        gadoController = new GadoController();
        plantacaoController = new PlantacaoController();
        despesaController = new DespesaController();

        setTitle("Fazenda Cedro - Gestão Rural");
        setSize(1250, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        atualizarDashboard();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Toolbar
        JPanel toolBar = new JPanel(new BorderLayout());
        toolBar.setBackground(PRIMARY_COLOR);
        toolBar.setPreferredSize(new Dimension(100, 65));
        
        JLabel titleLabel = new JLabel("  Fazenda Cedro");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        toolBar.add(titleLabel, BorderLayout.WEST);

        JButton btnAtualizar = new JButton("Sincronizar Sistema");
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBackground(ACCENT_COLOR);
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnAtualizar.addActionListener(e -> {
            new SwingWorker<Void, Void>() {
                @Override protected Void doInBackground() {
                    funcionarioController.lerArquivo();
                    gadoController.lerArquivo();
                    plantacaoController.lerArquivo();
                    despesaController.lerArquivo();
                    return null;
                }
                @Override protected void done() {
                    atualizarDashboard();
                    refreshAllTables();
                    JOptionPane.showMessageDialog(MainView.this, "Dados sincronizados com sucesso!");
                }
            }.execute();
        });
        
        JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 15));
        btnWrapper.setOpaque(false);
        btnWrapper.add(btnAtualizar);
        toolBar.add(btnWrapper, BorderLayout.EAST);
        
        add(toolBar, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 14));
        tabbedPane.setBackground(Color.WHITE);

        tabbedPane.addTab("Dashboard", createDashboardPanel());
        tabbedPane.addTab("Funcionários", createFuncionarioPanel());
        tabbedPane.addTab("Gado", createGadoPanel());
        tabbedPane.addTab("Plantações", createPlantacaoPanel());
        tabbedPane.addTab("Financeiro", createFinanceiroPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createDashboardPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_COLOR);
        
        JPanel cardsPanel = new JPanel(new GridLayout(2, 4, 25, 25));
        cardsPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        cardsPanel.setOpaque(false);

        cardsPanel.add(createStatCard("Total de Gados", "0", ACCENT_COLOR, "gado"));
        cardsPanel.add(createStatCard("Funcionários", "0", SUCCESS_COLOR, "func"));
        cardsPanel.add(createStatCard("Plantações", "0", DANGER_COLOR, "plant"));
        cardsPanel.add(createStatCard("Saldo Financeiro", "R$ 0.00", PRIMARY_COLOR, "saldo"));
        
        cardsPanel.add(createStatCard("Área Plantada (ha)", "0.0", new Color(108, 92, 231), "hec"));
        cardsPanel.add(createStatCard("Machos", "0", new Color(45, 52, 54), "macho"));
        cardsPanel.add(createStatCard("Fêmeas", "0", new Color(253, 121, 168), "femea"));
        cardsPanel.add(createStatCard("Status Fazenda", "Ativa", SUCCESS_COLOR, "status"));

        mainPanel.add(cardsPanel, BorderLayout.CENTER);
        
        JLabel welcome = new JLabel("Painel Administrativo da Fazenda", SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 28));
        welcome.setForeground(PRIMARY_COLOR);
        welcome.setBorder(new EmptyBorder(35, 0, 0, 0));
        mainPanel.add(welcome, BorderLayout.NORTH);

        return mainPanel;
    }

    private JPanel createStatCard(String title, String value, Color color, String type) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTitle.setForeground(Color.GRAY);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblValue.setForeground(color);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);

        switch(type) {
            case "gado": lblTotalGados = lblValue; break;
            case "func": lblTotalFuncionarios = lblValue; break;
            case "plant": lblTotalPlantacoes = lblValue; break;
            case "hec": lblHectaresTotal = lblValue; break;
            case "macho": lblMachos = lblValue; break;
            case "femea": lblFemeas = lblValue; break;
            case "saldo": lblSaldoFinanceiro = lblValue; break;
        }

        return card;
    }

    private void addFormField(JPanel panel, String label, JComponent field, GridBagConstraints gbc, int y) {
        gbc.gridx = 0; gbc.gridy = y;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        panel.add(lbl, gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
    }

    // --- CRUD FUNCIONARIOS ---
    private DefaultTableModel modelFunc;
    private JTable tableFunc;
    private JTextField txtFuncNome, txtFuncCpf, txtFuncSalario, txtFuncTelefone;

    private JPanel createFuncionarioPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, " CADASTRO DE FUNCIONÁRIO ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.BOLD, 14), PRIMARY_COLOR),
            new EmptyBorder(15, 15, 15, 15)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);

        txtFuncNome = new JTextField(20); txtFuncCpf = new JTextField(20);
        txtFuncSalario = new JTextField(20); txtFuncTelefone = new JTextField(20);

        addFormField(form, "Nome Completo:", txtFuncNome, gbc, 0);
        addFormField(form, "CPF:", txtFuncCpf, gbc, 1);
        addFormField(form, "Salário (R$):", txtFuncSalario, gbc, 2);
        addFormField(form, "Telefone:", txtFuncTelefone, gbc, 3);

        modelFunc = new DefaultTableModel(new String[]{"Nome", "CPF", "Salário", "Telefone"}, 0);
        tableFunc = new JTable(modelFunc);
        tableFunc.setRowHeight(25);
        
        JButton btnSalvar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Salvar Edição");
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(255, 121, 121));
        btnExcluir.setForeground(Color.WHITE);

        btnSalvar.addActionListener(e -> {
            try {
                double salario = Double.parseDouble(txtFuncSalario.getText());
                String result = funcionarioController.adicionarFuncionario(txtFuncNome.getText(), txtFuncCpf.getText(), salario, txtFuncTelefone.getText());
                if (result.equals("Sucesso")) {
                    funcionarioController.escreverArquivo();
                    refreshFuncTable(); atualizarDashboard(); clearFuncFields();
                    JOptionPane.showMessageDialog(this, "Funcionário cadastrado!");
                } else JOptionPane.showMessageDialog(this, result, "Aviso", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Verifique o valor do salário."); }
        });

        btnEditar.addActionListener(e -> {
            int row = tableFunc.getSelectedRow();
            if (row != -1) {
                funcionarioController.removerFuncionario((String) modelFunc.getValueAt(row, 1));
                funcionarioController.adicionarFuncionario(txtFuncNome.getText(), txtFuncCpf.getText(), Double.parseDouble(txtFuncSalario.getText()), txtFuncTelefone.getText());
                funcionarioController.escreverArquivo();
                refreshFuncTable(); atualizarDashboard(); clearFuncFields();
                JOptionPane.showMessageDialog(this, "Editado com sucesso!");
            }
        });

        btnExcluir.addActionListener(e -> {
            int row = tableFunc.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?") == 0) {
                funcionarioController.removerFuncionario((String) modelFunc.getValueAt(row, 1));
                refreshFuncTable(); atualizarDashboard();
            }
        });

        tableFunc.getSelectionModel().addListSelectionListener(e -> {
            int row = tableFunc.getSelectedRow();
            if (row != -1) {
                txtFuncNome.setText((String) modelFunc.getValueAt(row, 0));
                txtFuncCpf.setText((String) modelFunc.getValueAt(row, 1));
                txtFuncSalario.setText(modelFunc.getValueAt(row, 2).toString());
                txtFuncTelefone.setText((String) modelFunc.getValueAt(row, 3));
            }
        });

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnSalvar); actions.add(btnEditar); actions.add(btnExcluir);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(tableFunc), BorderLayout.CENTER);
        panel.add(actions, BorderLayout.SOUTH);

        refreshFuncTable();
        return panel;
    }

    // --- CRUD GADO ---
    private DefaultTableModel modelGado;
    private JTable tableGado;
    private JTextField txtGadoNome, txtGadoRgn, txtGadoRaca, txtGadoProp, txtGadoSerie, txtGadoFilhos, txtGadoNasc, txtGadoCat;
    private JComboBox<String> cbGadoSexo;

    private JPanel createGadoPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder(null, " CADASTRO DE ANIMAL ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.BOLD, 14), PRIMARY_COLOR));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 15, 6, 15);

        txtGadoNome = new JTextField(15); txtGadoRgn = new JTextField(15); txtGadoRaca = new JTextField(15);
        txtGadoProp = new JTextField(15); txtGadoSerie = new JTextField(15); txtGadoFilhos = new JTextField(15);
        txtGadoNasc = new JTextField(15); txtGadoCat = new JTextField(15);
        cbGadoSexo = new JComboBox<>(new String[]{"Macho", "Fêmea"});

        addFormField(form, "Nome:", txtGadoNome, gbc, 0);
        addFormField(form, "RGN:", txtGadoRgn, gbc, 1);
        addFormField(form, "Raça:", txtGadoRaca, gbc, 2);
        addFormField(form, "Proprietário:", txtGadoProp, gbc, 3);
        addFormField(form, "Série:", txtGadoSerie, gbc, 4);

        gbc.gridx = 2; gbc.gridy = 0; form.add(new JLabel("Data Nascimento:"), gbc);
        gbc.gridx = 3; form.add(txtGadoNasc, gbc);
        gbc.gridx = 2; gbc.gridy = 1; form.add(new JLabel("Categoria:"), gbc);
        gbc.gridx = 3; form.add(txtGadoCat, gbc);
        gbc.gridx = 2; gbc.gridy = 2; form.add(new JLabel("Número Filhos:"), gbc);
        gbc.gridx = 3; form.add(txtGadoFilhos, gbc);
        gbc.gridx = 2; gbc.gridy = 3; form.add(new JLabel("Sexo:"), gbc);
        gbc.gridx = 3; form.add(cbGadoSexo, gbc);

        modelGado = new DefaultTableModel(new String[]{"Nome", "RGN", "Raça", "Sexo", "Proprietário"}, 0);
        tableGado = new JTable(modelGado);
        tableGado.setRowHeight(25);

        JButton btnSalvar = new JButton("Adicionar");
        btnSalvar.addActionListener(e -> {
            try {
                String res = gadoController.adicionarGado(txtGadoNome.getText(), txtGadoNasc.getText(), txtGadoRaca.getText(),
                    txtGadoProp.getText(), txtGadoSerie.getText(), Long.parseLong(txtGadoRgn.getText()),
                    txtGadoCat.getText(), Byte.parseByte(txtGadoFilhos.getText()), cbGadoSexo.getSelectedIndex() == 0);
                if(res.equals("Sucesso")) { gadoController.escreverArquivo(); refreshGadoTable(); atualizarDashboard(); clearGadoFields(); }
                else JOptionPane.showMessageDialog(this, res);
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Verifique os números de RGN e Filhos."); }
        });

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(255, 121, 121));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.addActionListener(e -> {
            int row = tableGado.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Excluir?") == 0) {
                gadoController.removerGado((long) modelGado.getValueAt(row, 1));
                refreshGadoTable(); atualizarDashboard();
            }
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(tableGado), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnSalvar); actions.add(btnExcluir);
        panel.add(actions, BorderLayout.SOUTH);

        refreshGadoTable();
        return panel;
    }

    // --- CRUD PLANTAÇÕES ---
    private DefaultTableModel modelPlant;
    private JTable tablePlant;
    private JTextField txtPlantTipo, txtPlantHec;
    private JCheckBox chkPlantAgro;

    private JPanel createPlantacaoPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, " REGISTRO DE CULTIVO ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.BOLD, 14), PRIMARY_COLOR),
            new EmptyBorder(20, 20, 20, 20)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 20, 12, 20);

        txtPlantTipo = new JTextField(20);
        txtPlantHec = new JTextField(20);
        chkPlantAgro = new JCheckBox("Uso de Agrotóxicos");
        chkPlantAgro.setBackground(Color.WHITE);

        addFormField(form, "Tipo de Cultura:", txtPlantTipo, gbc, 0);
        addFormField(form, "Área (Hectares):", txtPlantHec, gbc, 1);

        gbc.gridx = 1; gbc.gridy = 2;
        form.add(chkPlantAgro, gbc);

        modelPlant = new DefaultTableModel(new String[]{"Cultura", "Área (ha)", "Data Plantio", "Colheita Prevista", "Agro"}, 0);
        tablePlant = new JTable(modelPlant);
        tablePlant.setRowHeight(25);

        JButton btnSalvar = new JButton("Registrar Plantio");
        btnSalvar.addActionListener(e -> {
            try {
                String res = plantacaoController.adicionarPlantacao(txtPlantTipo.getText(), new Date(), Double.parseDouble(txtPlantHec.getText()), chkPlantAgro.isSelected());
                if (res.equals("Sucesso")) { refreshPlantTable(); atualizarDashboard(); clearPlantFields(); }
                else JOptionPane.showMessageDialog(this, res);
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Hectares inválido."); }
        });

        JButton btnExcluir = new JButton("Remover Registro");
        btnExcluir.setBackground(new Color(255, 121, 121));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.addActionListener(e -> {
            int row = tablePlant.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Remover?") == 0) {
                Plantacao p = plantacaoController.getPlantacoesLista().get(row);
                plantacaoController.removerPlantacao(p.getTipo(), p.getDataPlantacao());
                refreshPlantTable(); atualizarDashboard();
            }
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablePlant), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnSalvar); actions.add(btnExcluir);
        panel.add(actions, BorderLayout.SOUTH);

        refreshPlantTable();
        return panel;
    }

    // --- CRUD FINANCEIRO ---
    private DefaultTableModel modelFin;
    private JTable tableFin;
    private JTextField txtFinDesc, txtFinValor;
    private JComboBox<String> cbFinTipo, cbFinCat;

    private JPanel createFinanceiroPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, " MOVIMENTAÇÃO FINANCEIRA ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.BOLD, 14), PRIMARY_COLOR),
            new EmptyBorder(15, 15, 15, 15)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);

        txtFinDesc = new JTextField(20);
        txtFinValor = new JTextField(20);
        cbFinTipo = new JComboBox<>(new String[]{"GASTO", "GANHO"});
        cbFinCat = new JComboBox<>(new String[]{"GADO", "FUNCIONARIO", "PLANTACAO", "CONSERTOS", "CONTRATACAO", "OUTROS"});

        addFormField(form, "Descrição:", txtFinDesc, gbc, 0);
        addFormField(form, "Tipo:", cbFinTipo, gbc, 1);
        addFormField(form, "Categoria:", cbFinCat, gbc, 2);
        addFormField(form, "Valor (R$):", txtFinValor, gbc, 3);

        modelFin = new DefaultTableModel(new String[]{"ID", "Descrição", "Tipo", "Categoria", "Valor", "Data"}, 0);
        tableFin = new JTable(modelFin);
        tableFin.setRowHeight(25);

        JButton btnSalvar = new JButton("Registrar");
        btnSalvar.addActionListener(e -> {
            try {
                String res = despesaController.adicionarDespesa(txtFinDesc.getText(), cbFinTipo.getSelectedItem().toString(),
                    cbFinCat.getSelectedItem().toString(), Double.parseDouble(txtFinValor.getText()));
                if (res.equals("Sucesso")) { refreshFinTable(); atualizarDashboard(); clearFinFields(); }
                else JOptionPane.showMessageDialog(this, res);
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Valor inválido."); }
        });

        JButton btnExcluir = new JButton("Remover");
        btnExcluir.setBackground(new Color(255, 121, 121));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.addActionListener(e -> {
            int row = tableFin.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Remover?") == 0) {
                despesaController.removerDespesa((int) modelFin.getValueAt(row, 0));
                refreshFinTable(); atualizarDashboard();
            }
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(tableFin), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnSalvar); actions.add(btnExcluir);
        panel.add(actions, BorderLayout.SOUTH);

        refreshFinTable();
        return panel;
    }

    // --- AUXILIARES ---
    private void atualizarDashboard() {
        List<Gado> gados = gadoController.getGadosLista();
        List<Plantacao> plants = plantacaoController.getPlantacoesLista();
        
        lblTotalGados.setText(String.valueOf(gados.size()));
        lblTotalFuncionarios.setText(String.valueOf(funcionarioController.getFuncionariosLista().size()));
        lblTotalPlantacoes.setText(String.valueOf(plants.size()));
        
        double totalHec = 0;
        for(Plantacao p : plants) totalHec += p.getHectares();
        lblHectaresTotal.setText(String.format("%.1f", totalHec));
        
        long machos = gados.stream().filter(Gado::isSexo).count();
        lblMachos.setText(String.valueOf(machos));
        lblFemeas.setText(String.valueOf(gados.size() - machos));

        double saldo = despesaController.calcularSaldo();
        lblSaldoFinanceiro.setText(String.format("R$ %.2f", saldo));
        lblSaldoFinanceiro.setForeground(saldo >= 0 ? SUCCESS_COLOR : DANGER_COLOR);
    }

    private void refreshAllTables() { refreshFuncTable(); refreshGadoTable(); refreshPlantTable(); refreshFinTable(); }

    private void refreshFuncTable() {
        modelFunc.setRowCount(0);
        for (Funcionario f : funcionarioController.getFuncionariosLista()) modelFunc.addRow(new Object[]{f.getNome(), f.getCpf(), f.getSalario(), f.getTelefone()});
    }

    private void refreshGadoTable() {
        modelGado.setRowCount(0);
        for (Gado g : gadoController.getGadosLista()) modelGado.addRow(new Object[]{g.getNome(), g.getRgn(), g.getRaca(), g.isSexo() ? "Macho" : "Fêmea", g.getProprietario()});
    }

    private void refreshPlantTable() {
        modelPlant.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Plantacao p : plantacaoController.getPlantacoesLista()) modelPlant.addRow(new Object[]{p.getTipo(), p.getHectares(), sdf.format(p.getDataPlantacao()), sdf.format(p.getDataColheita()), p.isAgrotoxicos() ? "Sim" : "Não"});
    }

    private void refreshFinTable() {
        modelFin.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Despesa d : despesaController.getDespesasLista()) modelFin.addRow(new Object[]{d.getId(), d.getDescricao(), d.getTipo(), d.getCategoria(), d.getValor(), sdf.format(d.getData())});
    }

    private void clearFuncFields() { txtFuncNome.setText(""); txtFuncCpf.setText(""); txtFuncSalario.setText(""); txtFuncTelefone.setText(""); }
    private void clearGadoFields() { 
        txtGadoNome.setText(""); txtGadoRgn.setText(""); txtGadoRaca.setText(""); txtGadoProp.setText(""); 
        txtGadoSerie.setText(""); txtGadoFilhos.setText(""); txtGadoNasc.setText(""); txtGadoCat.setText(""); 
    }
    private void clearPlantFields() { txtPlantTipo.setText(""); txtPlantHec.setText(""); chkPlantAgro.setSelected(false); }
    private void clearFinFields() { txtFinDesc.setText(""); txtFinValor.setText(""); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }
}
