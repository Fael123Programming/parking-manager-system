package br.com.rafael.main;

import br.com.rafael.classes.vacancy.Vacancy;
import br.com.rafael.classes.automobile.Automobile;
import br.com.rafael.classes.customer.Customer;
import br.com.rafael.classes.read_input_data.Input;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = Input.getInstance(); //Singleton!
        ArrayList<Vacancy> carsParking = new ArrayList<>();
        ArrayList<Vacancy> motosParking = new ArrayList<>();
        int chosen;
        double carsHourPrice = 10.00, motosHourPrice = 5.00;
        //Menus
        String[] optsMainMenu = {"(1) Carros", "(2) Motos", "(3) Configurações",
                "(4) Sair"}, optsSubMenu = {"(1) Registrar", "(2) Ver re"
                + "gistrados", "(3) Pesquisar vaga", "(4) Cobrar vaga", "(5) Limpar dados",
                "(6) Voltar ao menu principal"}, settingsMenu = {"(1) Preços",
                "(2) Capacidade e lotação", "(3) Segurança",
                "(4) Documentação", "(5) Voltar ao menu principal"}, parkingInfoMenu =
                {"(1) Capacidade", "(2) Lotação", "(3) Voltar a configurações"};
        //----------------------------------------------------------------------
        String vacIdent, opt, password = "java", carsParkingCapacity = "<undefined>", motosParkingCapacity = "<undefined>";
        boolean controlVariable01 = true, controlVariable02 = true, controlVariable03 = true;
        while (true) {
            if (verifyUser(password)) {
                while (true) {
                    chosen = menu(optsMainMenu, "Gerenciador de Estacionamentos v 1.0.0");
                    String title;
                    switch (chosen) {
                        case 1://Cars branch
                            while (controlVariable01) {
                                if (isInteger(carsParkingCapacity)) {
                                    title = String.format("Estacionamento - Carros"
                                            + " (Cap.: %s,Lot.: %d)", carsParkingCapacity, carsParking.size());
                                } else title = String.format("Estacionamento - Carros (Lot.: %d)", carsParking.size());
                                chosen = menu(optsSubMenu, title);
                                switch (chosen) {
                                    case 1:
                                        if (isInteger(carsParkingCapacity)) {
                                            if (carsParking.size() >= Integer.parseInt(carsParkingCapacity)) {
                                                message("Não há mais vagas", 100, true, false);
                                                break;
                                            }
                                        }
                                        Customer newCust = new Customer();
                                        Automobile newAuto = new Automobile();

                                        message("Cliente", 100, true, true);
                                        System.out.print("Nome:");
                                        newCust.setName(input.nextLine());
                                        System.out.print("Idade:");
                                        newCust.setAge(input.nextLine());
                                        System.out.print("Telefone:");
                                        newCust.setPhoneNumber(input.nextLine());

                                        message("Carro", 100, true, true);
                                        System.out.print("Marca:");
                                        newAuto.setBrand(input.nextLine());
                                        System.out.print("Modelo:");
                                        newAuto.setModel(input.nextLine());
                                        System.out.print("Placa:");
                                        newAuto.setLicensePlate(input.nextLine());
                                        System.out.print("Ano:");
                                        newAuto.setYear(input.nextLine());
                                        while (true) {
                                            row(100);
                                            System.out.print("Identificação da vaga:");
                                            vacIdent = input.nextLine();
                                            if (isVacancyEmpty(carsParking, vacIdent)) break;
                                            message("Vaga já ocupada", 100, true, false);
                                        }
                                        Vacancy newVacancy = new Vacancy(newCust, newAuto,
                                                vacIdent, carsHourPrice);
                                        carsParking.add(newVacancy);
                                        message("Registrado", 100, true, false);
                                        break;
                                    case 2:
                                        if (carsParking.isEmpty()) {
                                            message("Sem registros", 100, true, false);
                                            break;
                                        }
                                        message("Carros registrados", 100, true, true);
                                        System.out.printf("%-55s%s%n", "Vaga", "Carro");
                                        for (Vacancy vc : carsParking) {
                                            System.out.printf("%-55s%s%n", vc.
                                                            getVacancyIdent(),
                                                    vc.getCustomersAuto().getBrand()
                                                            + " " + vc.getCustomersAuto().getModel());
                                        }
                                        break;
                                    case 3:
                                        if (carsParking.isEmpty()) {
                                            message("Sem registros", 100, true, false);
                                            break;
                                        }
                                        while (true) {
                                            row(100);
                                            System.out.print("Identificação da vaga (\"sair\" para voltar):");
                                            vacIdent = input.nextLine();
                                            if (vacIdent.toLowerCase().equals("sair")) break;
                                            searchInArrayList(carsParking, vacIdent);
                                        }
                                        break;
                                    case 4:
                                        while (true) {
                                            if (carsParking.isEmpty()) {
                                                message("Sem registros", 100, true, false);
                                                break;
                                            }
                                            row(100);
                                            System.out.print("Identificação da vaga (\"sair\" para voltar) :");
                                            vacIdent = input.nextLine();
                                            if (vacIdent.toLowerCase().equals("sair")) break;
                                            demandVacancy(carsParking, vacIdent);
                                        }
                                        break;
                                    case 5:
                                        if (carsParking.isEmpty()) {
                                            message("Sem registros", 100, true, false);
                                            break;
                                        }
                                        while (controlVariable02) {
                                            row(100);
                                            System.out.print("\"Limpar\" para prosseguir ("
                                                    + "\"sair\" para cancelar):");
                                            opt = input.nextLine();
                                            switch (opt.toLowerCase()) {
                                                case "limpar":
                                                    carsParking.clear();
                                                    message("Limpo com sucesso", 100, true, false);
                                                case "sair":
                                                    controlVariable02 = false;
                                                    break;
                                                default:
                                                    message("Escolha uma opção válida", 100, true, false);
                                            }
                                        }
                                        controlVariable02 = true;
                                        break;
                                    case 6:
                                        controlVariable01 = false;
                                        break;
                                }
                            }
                            controlVariable01 = true;
                            break;
                        case 2: //Motorcycles branch
                            while (controlVariable01) {
                                if (isInteger(motosParkingCapacity)) {
                                    title = String.format("Estacionamento - Motos"
                                            + " (Cap.: %s,Lot.: %d)", motosParkingCapacity, motosParking.size());
                                } else title = String.format("Estacionamento - Motos (Lot.: %d)", motosParking.size());
                                chosen = menu(optsSubMenu, title);
                                switch (chosen) {
                                    case 1:
                                        if (isInteger(motosParkingCapacity)) {
                                            if (motosParking.size() >= Integer.parseInt(motosParkingCapacity)) {
                                                message("Não há mais vagas", 100, true, false);
                                                break;
                                            }
                                        }
                                        Customer newCust = new Customer();
                                        Automobile newAuto = new Automobile();

                                        message("Cliente", 100, true, true);
                                        System.out.print("Nome:");
                                        newCust.setName(input.nextLine());
                                        System.out.print("Idade:");
                                        newCust.setAge(input.nextLine());
                                        System.out.print("Telefone:");
                                        newCust.setPhoneNumber(input.nextLine());

                                        message("Moto", 100, true, true);
                                        System.out.print("Marca:");
                                        newAuto.setBrand(input.nextLine());
                                        System.out.print("Modelo:");
                                        newAuto.setModel(input.nextLine());
                                        System.out.print("Placa:");
                                        newAuto.setLicensePlate(input.nextLine());
                                        System.out.print("Ano:");
                                        newAuto.setYear(input.nextLine());
                                        while (true) {
                                            row(100);
                                            System.out.print("Identificação da vaga:");
                                            vacIdent = input.nextLine();
                                            if (isVacancyEmpty(motosParking, vacIdent)) break;
                                            message("Vaga já ocupada", 100, true, false);
                                        }
                                        Vacancy newVacancy = new Vacancy(newCust, newAuto,
                                                vacIdent, motosHourPrice);
                                        motosParking.add(newVacancy);
                                        message("Registrado", 100, true, false);
                                        break;
                                    case 2:
                                        if (motosParking.isEmpty()) {
                                            message("Sem registros",
                                                    100, true, false);
                                            break;
                                        }
                                        message("Motos registradas", 100, true, true);
                                        System.out.printf("%-55s%s%n", "Vaga", "Moto");
                                        for (Vacancy vc : motosParking) {
                                            System.out.printf("%-55s%s%n", vc.
                                                            getVacancyIdent(),
                                                    vc.getCustomersAuto().getBrand() +
                                                            " " + vc.getCustomersAuto()
                                                            .getModel());
                                        }
                                        break;
                                    case 3:
                                        if (motosParking.isEmpty()) {
                                            message("Sem registros", 100, true, false);
                                            break;
                                        }
                                        while (true) {
                                            row(100);
                                            System.out.print("Identificação da vaga (\"sair\" para voltar):");
                                            vacIdent = input.nextLine();
                                            if (vacIdent.toLowerCase().equals("sair")) break;
                                            searchInArrayList(motosParking, vacIdent);
                                        }
                                        break;
                                    case 4:
                                        while (true) {
                                            if (motosParking.isEmpty()) {
                                                message("Sem registros", 100, true, false);
                                                break;
                                            }
                                            row(100);
                                            System.out.print("Identificação da vaga (\"sair\" para voltar) :");
                                            vacIdent = input.nextLine();
                                            if (vacIdent.toLowerCase().equals("sair")) break;
                                            demandVacancy(motosParking, vacIdent);
                                        }
                                        break;
                                    case 5:
                                        if (motosParking.isEmpty()) {
                                            message("Sem registros", 100, true, false);
                                            break;
                                        }
                                        while (controlVariable02) {
                                            row(100);
                                            System.out.print("\"Limpar\" para prosseguir ("
                                                    + "\"sair\" para cancelar):");
                                            opt = input.nextLine();
                                            switch (opt.toLowerCase()) {
                                                case "limpar":
                                                    motosParking.clear();
                                                    message("Limpo com sucesso", 100, true, false);
                                                case "sair":
                                                    controlVariable02 = false;
                                                    break;
                                                default:
                                                    message("Escolha uma opção válida", 100, true, false);
                                            }
                                        }
                                        controlVariable02 = true;
                                        break;
                                    case 6:
                                        controlVariable01 = false;
                                        break;
                                }
                            }
                            controlVariable01 = true;
                            break;
                        case 3: //System settings
                            if (verifyUser(password)) {
                                while (controlVariable01) {
                                    chosen = menu(settingsMenu, "Configurações");
                                    switch (chosen) {
                                        case 1:
                                            String strNewPrice;
                                            while (controlVariable02) {
                                                message("Preços", 100, true, true);
                                                System.out.printf("Carros => R$ %.2f"
                                                                + " a hora%nMotos => R$ %.2f"
                                                                + " a hora%n", carsHourPrice,
                                                        motosHourPrice);
                                                row(100);
                                                System.out.printf("Sempre antes de alterar os preços,certifique-se%n"
                                                        + " que o sistema não tenha nenhum dado registrado.%nAssim,evita-se "
                                                        + "erros com o cálculo do preço a ser cobrado.%n");
                                                row(100);
                                                System.out.printf("Para alterar os valores,"
                                                        + "digite a categoria escolhida%n(\"carros\" ou \"motos\")."
                                                        + "Para voltar,digite \"sair\":");
                                                opt = input.nextLine();
                                                switch (opt.toLowerCase()) {
                                                    case "carros":
                                                        row(100);
                                                        System.out.print("Novo valor: R$ ");
                                                        strNewPrice = input.nextLine();
                                                        carsHourPrice = Double.parseDouble(strNewPrice);
                                                        message("Valor alterado", 100, true, false);
                                                        break;
                                                    case "motos":
                                                        row(100);
                                                        System.out.print("Novo valor: R$ ");
                                                        strNewPrice = input.nextLine();
                                                        motosHourPrice = Double.parseDouble(strNewPrice);
                                                        message("Valor alterado", 100, true, false);
                                                        break;
                                                    case "sair":
                                                        controlVariable02 = false;
                                                        break;
                                                    default:
                                                        message("Escolha uma opção válida", 100, true, false);
                                                }
                                            }
                                            controlVariable02 = true;
                                            break;
                                        case 2:
                                            while (controlVariable02) {
                                                chosen = menu(parkingInfoMenu, "Capacidade e lotação");
                                                switch (chosen) {
                                                    case 1:
                                                        while (controlVariable03) {
                                                            message("Capacidade", 100, true, true);
                                                            System.out.printf("Ala para carros: %s%nAla para motos: %s%n",
                                                                    carsParkingCapacity, motosParkingCapacity);
                                                            row(100);
                                                            System.out.printf("Para definir a capacidade,digite a categoria "
                                                                    + "escolhida%n(\"carros\" ou \"motos\").Para voltar,digite \"sair\":");
                                                            opt = input.nextLine();
                                                            String newCapacity;
                                                            switch (opt.toLowerCase()) {
                                                                case "carros":
                                                                    row(100);
                                                                    System.out.print("Nova capacidade:");
                                                                    newCapacity = input.nextLine();
                                                                    if (isInteger(newCapacity)) {
                                                                        message("Capacidade alterada", 100, true, false);
                                                                        carsParkingCapacity = newCapacity;
                                                                    } else message("Valor inválido", 100, true, false);
                                                                    break;
                                                                case "motos":
                                                                    row(100);
                                                                    System.out.print("Nova capacidade:");
                                                                    newCapacity = input.nextLine();
                                                                    if (isInteger(newCapacity)) {
                                                                        message("Capacidade alterada", 100, true, false);
                                                                        motosParkingCapacity = newCapacity;
                                                                    } else message("Valor inválido", 100, true, false);
                                                                    break;
                                                                case "sair":
                                                                    controlVariable03 = false;
                                                                    break;
                                                                default:
                                                                    message("Escolha uma opção válida", 100, true, false);
                                                            }
                                                        }
                                                        controlVariable03 = true;
                                                        break;
                                                    case 2:
                                                        message("Lotação", 100, true, true);
                                                        System.out.printf("Ala de carros: %d%n", carsParking.size());
                                                        System.out.printf("Ala de motos: %d%n", motosParking.size());
                                                        break;
                                                    case 3:
                                                        controlVariable02 = false;
                                                        break;
                                                }
                                            }
                                            controlVariable02 = true;
                                            break;
                                        case 3:
                                            while (controlVariable02) {
                                                message("Segurança", 100, true, true);
                                                System.out.printf("Senha: %s%n", password);
                                                row(100);
                                                System.out.printf("Para mudá-la,digite \"nova senha\".%n"
                                                        + "Para voltar,digite \"sair\":");
                                                opt = input.nextLine();
                                                switch (opt.toLowerCase()) {
                                                    case "nova senha":
                                                        row(100);
                                                        System.out.print("Nova senha:");
                                                        password = input.nextLine();
                                                        message("Senha modificada", 100, true, false);
                                                        break;
                                                    case "sair":
                                                        controlVariable02 = false;
                                                        break;
                                                    default:
                                                        message("Escolha uma opção válida", 100, true, false);
                                                }
                                            }
                                            controlVariable02 = true;
                                            break;
                                        case 4:
                                            while (true) {
                                                message("Documentação", 100, true, true);
                                                System.out.printf(""
                                                        + "Versão: 1.0.0%n%n"
                                                        + "Sobre a aplicação: desenvolvido"
                                                        + " para fins acadêmi-%ncos e "
                                                        + "de estudo do núcleo base da lin"
                                                        + "guagem Java.%nNão foi usado nenhu"
                                                        + "ma ferramenta CASE auxiliar,%nsomente "
                                                        + "Netbeans IDE 8.2 e Java \"puro\".%n%n"
                                                        + "Propósito geral: simular um sistema gerenciador "
                                                        + "de %nestacionamentos,que realiza o controle de vagas e%nlocais disponíveis,"
                                                        + "nível de lotação,tempo gasto por%ncada automóvel e cobrança de taxas.%n%n"
                                                        + "Autor: Rafael Q.F.Guimarães%n"
                                                );
                                                row(100);
                                                System.out.print("Digite \"sair\" para voltar:");
                                                opt = input.nextLine();
                                                if (opt.toLowerCase().equals("sair")) break;
                                            }
                                            break;
                                        case 5:
                                            controlVariable01 = false;
                                            break;
                                    }
                                }
                                controlVariable01 = true;
                            } else message("Para acessar essa opção é preciso ter a senha", 100, true, false);
                            break;
                        case 4:
                            message("Rotina finalizada", 100, true, true);
                            input.close();
                            return;
                    }
                }
            } else message("Para entrar no sistema é preciso ter a senha", 100, true, false);
        }
    }

    public static boolean isInteger(String str) {
        String numbers = "0123456789";
        for (int perStr = 0; perStr < str.length(); perStr++) {
            if (numbers.indexOf(str.charAt(perStr)) == -1) return false;
        }
        return true;
    }

    public static boolean verifyUser(String password) {
        Scanner input = Input.getInstance();
        row(100);
        System.out.print("Digite a senha: ");
        return input.nextLine().equals(password);
    }

    public static boolean isVacancyEmpty(ArrayList<Vacancy> al, String vacIdent) {
        for (Vacancy vc : al) {
            if (vc.getVacancyIdent().equals(vacIdent)) return false;
        }
        return true;
    }

    public static void demandVacancy(ArrayList<Vacancy> al, String vacIdent) {
        for (Vacancy vc : al) {
            if (vc.getVacancyIdent().equals(vacIdent)) {
                row(100);
                System.out.printf("Tempo total: %s%nValor a cobrar: R$ %.2f%n",
                        vc.calculateTotalTime(), vc.calculatePrice(vc.getHourPrice()));
                message("Finalizado", 100, true, false);
                al.remove(vc);
                return;
            }
        }
        message("Vaga não encontrada", 100, true, false);
    }

    public static void searchInArrayList(ArrayList<Vacancy> al, String vacIdent) {
        for (Vacancy vc : al) {
            if (vc.getVacancyIdent().equals(vacIdent)) {
                System.out.println(vc);
                return;
            }
        }
        message("Vaga não encontrada", 100, true, false);
    }

    public static int menu(String[] opts, String msg) {
        Scanner input = Input.getInstance();
        int chosen = 0;
        while (true) {
            message(msg, 100, true, true);
            for (String opt : opts) System.out.println(opt);
            row(100);
            try {
                chosen = input.nextInt();
            } catch (InputMismatchException exception) {
                message("Digite um número para escolher uma opção", 100, true, false);
                break;
            }
            if (chosen >= 1 && chosen <= opts.length) break;
            else message("Escolha uma opção válida", 100, true, false);
        }
        return chosen;
    }

    public static void message(String msg, int sizeDashes, boolean showTopRow, boolean showBottomRow) {
        String dashes = "";
        while (dashes.length() <= sizeDashes) {
            dashes += "-";
        }
        if (showTopRow) System.out.println(dashes);
        System.out.println(fillString(msg, sizeDashes));
        if (showBottomRow) System.out.println(dashes);
    }

    public static void row(int size) {
        String dashes = "";
        while (dashes.length() <= size) {
            dashes += "-";
        }
        System.out.println(dashes);
    }

    public static String fillString(String str, int totalSpaces) {
        while (str.length() < totalSpaces) {
            if (str.length() % 2 == 0) str += " ";
            else str = " " + str;
        }
        return str;
    }
}
