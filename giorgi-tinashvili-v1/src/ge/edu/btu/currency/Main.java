package ge.edu.btu.currency;

import ge.edu.btu.currency.service.impl.CurrencyServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CurrencyServiceImpl currencyService = new CurrencyServiceImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("შემოიტანე კურსი :");
        double r = scanner.nextDouble();
        System.out.println("კურსი არის "+currencyService.setExchangeRate(r));
        System.out.println("შემოიტანე რამდენის გადახურდავე გსურს: ");
        double v = scanner.nextDouble();
        System.out.println("მიღებული თანხა გადახურდავების შემდეგ "+currencyService.convert(v));
    }
}
