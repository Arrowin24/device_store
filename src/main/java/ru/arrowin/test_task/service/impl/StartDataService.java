package ru.arrowin.test_task.service.impl;

import org.springframework.stereotype.Service;
import ru.arrowin.test_task.model.devices.*;
import ru.arrowin.test_task.model.models.*;
import ru.arrowin.test_task.service.repository.device.*;
import ru.arrowin.test_task.service.repository.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StartDataService {

    private final PCRepository pcRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final SmartPhoneRepository smartPhoneRepository;
    private final TVRepository tvRepository;
    private final VacuumRepository vacuumRepository;

    private final PCModelRepository pcModelRepository;
    private final RefrigeratorModelRepository refrigeratorModelRepository;
    private final SmartPhoneModelRepository smartPhoneModelRepository;
    private final TVModelRepository tvModelRepository;
    private final VacuumModelRepository vacuumModelRepository;

    public StartDataService(
            PCRepository pcRepository, RefrigeratorRepository refrigeratorRepository,
            SmartPhoneRepository smartPhoneRepository, TVRepository tvRepository, VacuumRepository vacuumRepository,
            PCModelRepository pcModelRepository, RefrigeratorModelRepository refrigeratorModelRepository,
            SmartPhoneModelRepository smartPhoneModelRepository, TVModelRepository tvModelRepository,
            VacuumModelRepository vacuumModelRepository)
    {
        this.pcRepository = pcRepository;
        this.refrigeratorRepository = refrigeratorRepository;
        this.smartPhoneRepository = smartPhoneRepository;
        this.tvRepository = tvRepository;
        this.vacuumRepository = vacuumRepository;
        this.pcModelRepository = pcModelRepository;
        this.refrigeratorModelRepository = refrigeratorModelRepository;
        this.smartPhoneModelRepository = smartPhoneModelRepository;
        this.tvModelRepository = tvModelRepository;
        this.vacuumModelRepository = vacuumModelRepository;
    }

    public void uploadStartData() {
        uploadPC();
        uploadRefrigerator();
        uploadSmartphone();
        uploadTV();
        uploadVacuum();
    }

    public List<String> getAllData() {
        List<Model> models = new ArrayList<>();
        models.addAll(pcModelRepository.findAll());
        models.addAll(refrigeratorModelRepository.findAll());
        models.addAll(smartPhoneModelRepository.findAll());
        models.addAll(tvModelRepository.findAll());
        models.addAll(vacuumModelRepository.findAll());
        return models.stream().map(Model::toText).collect(Collectors.toList());
    }

    private void uploadPC() {
        PC pc1 = new PC();
        pc1.setId(1);
        pc1.setDeviceName("Philips");
        pc1.setCountry("USA");
        pc1.setManufacturer("Philips Co.");
        pc1.setOnlineOrderAvailable(true);
        pc1.setInstallmentAvailable(true);


        PC pc2 = new PC();
        pc2.setId(2);
        pc2.setDeviceName("Dragon");
        pc2.setCountry("China");
        pc2.setManufacturer("Taiwan Co.");
        pc2.setOnlineOrderAvailable(false);
        pc2.setInstallmentAvailable(false);

        PC pc3 = new PC();
        pc3.setId(3);
        pc3.setDeviceName("Apple");
        pc3.setCountry("USA");
        pc3.setManufacturer("Apple Co.");
        pc3.setOnlineOrderAvailable(true);
        pc3.setInstallmentAvailable(false);


        PCModel pcModel11 = new PCModel();
        pcModel11.setSerialNum("A123");
        pcModel11.setModelName("Philips A1");
        pcModel11.setColor("Black");
        pcModel11.setPrice(15_000);
        pcModel11.setSizeHeight(10);
        pcModel11.setSizeLength(15);
        pcModel11.setSizeWidth(10);
        pcModel11.setAvailable(true);
        pcModel11.setCategory("First");
        pcModel11.setPc(pc1);

        PCModel pcModel12 = new PCModel();
        pcModel12.setSerialNum("B");
        pcModel12.setModelName("Philips A1");
        pcModel12.setColor("Yellow");
        pcModel12.setPrice(20_000);
        pcModel12.setSizeHeight(10);
        pcModel12.setSizeLength(15);
        pcModel12.setSizeWidth(10);
        pcModel12.setAvailable(true);
        pcModel12.setCategory("First");
        pcModel12.setPc(pc1);

        PCModel pcModel21 = new PCModel();
        pcModel21.setSerialNum("C");
        pcModel21.setModelName("Dragon C1");
        pcModel21.setColor("Black");
        pcModel21.setPrice(65_000);
        pcModel21.setSizeHeight(10);
        pcModel21.setSizeLength(15);
        pcModel21.setSizeWidth(10);
        pcModel21.setAvailable(true);
        pcModel21.setPc(pc2);

        PCModel pcModel22 = new PCModel();
        pcModel22.setSerialNum("D");
        pcModel22.setModelName("Dragon CD1");
        pcModel22.setColor("White");
        pcModel22.setPrice(65_000);
        pcModel22.setSizeHeight(10);
        pcModel22.setSizeLength(15);
        pcModel22.setSizeWidth(10);
        pcModel22.setAvailable(true);
        pcModel22.setCategory("First");
        pcModel22.setProcessorType("Intel");
        pcModel22.setPc(pc2);

        PCModel pcModel31 = new PCModel();
        pcModel31.setSerialNum("E");
        pcModel31.setModelName("iMac A3");
        pcModel31.setColor("White");
        pcModel31.setPrice(100_000);
        pcModel31.setSizeHeight(10);
        pcModel31.setSizeLength(15);
        pcModel31.setSizeWidth(10);
        pcModel31.setAvailable(true);
        pcModel31.setCategory("First");
        pcModel31.setProcessorType("Intel");
        pcModel31.setPc(pc3);

        PCModel pcModel32 = new PCModel();
        pcModel32.setSerialNum("F");
        pcModel32.setModelName("iMac Pro");
        pcModel32.setColor("White");
        pcModel32.setPrice(100_000);
        pcModel32.setSizeHeight(10);
        pcModel32.setSizeLength(15);
        pcModel32.setSizeWidth(10);
        pcModel32.setAvailable(true);
        pcModel32.setCategory("First");
        pcModel32.setProcessorType("PENTIUM");
        pcModel32.setPc(pc3);

        pcRepository.save(pc1);
        pcRepository.save(pc2);
        pcRepository.save(pc3);

        pcModelRepository.save(pcModel11);
        pcModelRepository.save(pcModel12);
        pcModelRepository.save(pcModel21);
        pcModelRepository.save(pcModel22);
        pcModelRepository.save(pcModel31);
        pcModelRepository.save(pcModel32);
    }

    private void uploadRefrigerator() {
        Refrigerator refrigerator1 = new Refrigerator();
        refrigerator1.setId(1);
        refrigerator1.setDeviceName("Philips");
        refrigerator1.setCountry("USA");
        refrigerator1.setManufacturer("Philips Co.");
        refrigerator1.setOnlineOrderAvailable(true);
        refrigerator1.setInstallmentAvailable(true);


        Refrigerator refrigerator2 = new Refrigerator();
        refrigerator2.setId(2);
        refrigerator2.setDeviceName("Ural");
        refrigerator2.setCountry("Russia");
        refrigerator2.setManufacturer("UralTorg");
        refrigerator2.setOnlineOrderAvailable(true);
        refrigerator2.setInstallmentAvailable(true);

        Refrigerator refrigerator3 = new Refrigerator();
        refrigerator3.setId(3);
        refrigerator3.setDeviceName("TDD");
        refrigerator3.setCountry("Kazakhstan");
        refrigerator3.setManufacturer("KazH");
        refrigerator3.setOnlineOrderAvailable(true);
        refrigerator3.setInstallmentAvailable(false);

        RefrigeratorModel refrigeratorModel11 = new RefrigeratorModel();
        refrigeratorModel11.setSerialNum("REF12");
        refrigeratorModel11.setModelName("Philips R1");
        refrigeratorModel11.setColor("Black");
        refrigeratorModel11.setPrice(15_000);
        refrigeratorModel11.setSizeHeight(200);
        refrigeratorModel11.setSizeLength(50);
        refrigeratorModel11.setSizeWidth(65);
        refrigeratorModel11.setAvailable(true);
        refrigeratorModel11.setDoorsNum(2);
        refrigeratorModel11.setCompressorType("vacuum");
        refrigeratorModel11.setRefrigerator(refrigerator1);

        RefrigeratorModel refrigeratorModel12 = new RefrigeratorModel();
        refrigeratorModel12.setSerialNum("REF13");
        refrigeratorModel12.setModelName("Philips Geyser");
        refrigeratorModel12.setColor("White");
        refrigeratorModel12.setPrice(25_000);
        refrigeratorModel12.setSizeHeight(200);
        refrigeratorModel12.setSizeLength(50);
        refrigeratorModel12.setSizeWidth(65);
        refrigeratorModel12.setAvailable(true);
        refrigeratorModel12.setDoorsNum(4);
        refrigeratorModel12.setCompressorType("vacuum");
        refrigeratorModel12.setRefrigerator(refrigerator1);

        RefrigeratorModel refrigeratorModel21 = new RefrigeratorModel();
        refrigeratorModel21.setSerialNum("REF14");
        refrigeratorModel21.setModelName("Ural New");
        refrigeratorModel21.setColor("White");
        refrigeratorModel21.setPrice(10_000);
        refrigeratorModel21.setSizeHeight(220);
        refrigeratorModel21.setSizeLength(60);
        refrigeratorModel21.setSizeWidth(75);
        refrigeratorModel21.setAvailable(true);
        refrigeratorModel21.setDoorsNum(3);
        refrigeratorModel21.setCompressorType("vacuum");
        refrigeratorModel21.setRefrigerator(refrigerator2);

        RefrigeratorModel refrigeratorModel22 = new RefrigeratorModel();
        refrigeratorModel22.setSerialNum("REF144");
        refrigeratorModel22.setModelName("Ural Cryogenic");
        refrigeratorModel22.setColor("White");
        refrigeratorModel22.setPrice(17_000);
        refrigeratorModel22.setSizeHeight(200);
        refrigeratorModel22.setSizeLength(50);
        refrigeratorModel22.setSizeWidth(75);
        refrigeratorModel22.setAvailable(true);
        refrigeratorModel22.setDoorsNum(2);
        refrigeratorModel22.setCompressorType("vacuum");
        refrigeratorModel22.setRefrigerator(refrigerator2);

        RefrigeratorModel refrigeratorModel31 = new RefrigeratorModel();
        refrigeratorModel31.setSerialNum("REF15");
        refrigeratorModel31.setModelName("Cumys");
        refrigeratorModel31.setColor("White");
        refrigeratorModel31.setPrice(23_000);
        refrigeratorModel31.setSizeHeight(200);
        refrigeratorModel31.setSizeLength(50);
        refrigeratorModel31.setSizeWidth(75);
        refrigeratorModel31.setAvailable(true);
        refrigeratorModel31.setDoorsNum(2);
        refrigeratorModel31.setCompressorType("vacuum");
        refrigeratorModel31.setRefrigerator(refrigerator3);

        RefrigeratorModel refrigeratorModel32 = new RefrigeratorModel();
        refrigeratorModel32.setSerialNum("REF155");
        refrigeratorModel32.setModelName("Cumys Pro Max");
        refrigeratorModel32.setColor("Black");
        refrigeratorModel32.setPrice(40_000);
        refrigeratorModel32.setSizeHeight(200);
        refrigeratorModel32.setSizeLength(50);
        refrigeratorModel32.setSizeWidth(75);
        refrigeratorModel32.setAvailable(true);
        refrigeratorModel32.setDoorsNum(2);
        refrigeratorModel32.setCompressorType("Not included");
        refrigeratorModel32.setRefrigerator(refrigerator3);

        refrigeratorRepository.save(refrigerator1);
        refrigeratorRepository.save(refrigerator2);
        refrigeratorRepository.save(refrigerator3);

        refrigeratorModelRepository.save(refrigeratorModel11);
        refrigeratorModelRepository.save(refrigeratorModel12);
        refrigeratorModelRepository.save(refrigeratorModel21);
        refrigeratorModelRepository.save(refrigeratorModel22);
        refrigeratorModelRepository.save(refrigeratorModel31);
        refrigeratorModelRepository.save(refrigeratorModel32);
    }

    private void uploadSmartphone() {
        SmartPhone smartPhone1 = new SmartPhone();
        smartPhone1.setId(1);
        smartPhone1.setDeviceName("Apple");
        smartPhone1.setCountry("USA");
        smartPhone1.setManufacturer("Apple Co.");
        smartPhone1.setOnlineOrderAvailable(true);
        smartPhone1.setInstallmentAvailable(true);


        SmartPhone smartPhone2 = new SmartPhone();
        smartPhone2.setId(2);
        smartPhone2.setDeviceName("Xiaomi");
        smartPhone2.setCountry("China");
        smartPhone2.setManufacturer("Xiaomi Co.");
        smartPhone2.setOnlineOrderAvailable(true);
        smartPhone2.setInstallmentAvailable(true);

        SmartPhone smartPhone3 = new SmartPhone();
        smartPhone3.setId(3);
        smartPhone3.setDeviceName("Samsung");
        smartPhone3.setCountry("South Korea");
        smartPhone3.setManufacturer("Samsung Co.");
        smartPhone3.setOnlineOrderAvailable(false);
        smartPhone3.setInstallmentAvailable(true);


        SmartPhoneModel smartPhoneModel11 = new SmartPhoneModel();
        smartPhoneModel11.setSerialNum("Ph123");
        smartPhoneModel11.setModelName("iPhone 11");
        smartPhoneModel11.setColor("Black");
        smartPhoneModel11.setPrice(135_000);
        smartPhoneModel11.setSizeHeight(10);
        smartPhoneModel11.setSizeLength(15);
        smartPhoneModel11.setSizeWidth(10);
        smartPhoneModel11.setAvailable(true);
        smartPhoneModel11.setMemory(8);
        smartPhoneModel11.setCameraNums(2);
        smartPhoneModel11.setSmartphone(smartPhone1);

        SmartPhoneModel smartPhoneModel12 = new SmartPhoneModel();
        smartPhoneModel12.setSerialNum("Ph124");
        smartPhoneModel12.setModelName("iPhone 11 Pro");
        smartPhoneModel12.setColor("White");
        smartPhoneModel12.setPrice(165_000);
        smartPhoneModel12.setSizeHeight(10);
        smartPhoneModel12.setSizeLength(15);
        smartPhoneModel12.setSizeWidth(10);
        smartPhoneModel12.setAvailable(true);
        smartPhoneModel12.setMemory(8);
        smartPhoneModel12.setCameraNums(2);
        smartPhoneModel12.setSmartphone(smartPhone1);

        SmartPhoneModel smartPhoneModel21 = new SmartPhoneModel();
        smartPhoneModel21.setSerialNum("Ph125");
        smartPhoneModel21.setModelName("Redmi A3");
        smartPhoneModel21.setColor("Black");
        smartPhoneModel21.setPrice(16_000);
        smartPhoneModel21.setSizeHeight(10);
        smartPhoneModel21.setSizeLength(15);
        smartPhoneModel21.setSizeWidth(10);
        smartPhoneModel21.setAvailable(true);
        smartPhoneModel21.setMemory(12);
        smartPhoneModel21.setCameraNums(3);
        smartPhoneModel21.setSmartphone(smartPhone2);

        SmartPhoneModel smartPhoneModel22 = new SmartPhoneModel();
        smartPhoneModel22.setSerialNum("Ph126");
        smartPhoneModel22.setModelName("Redmi A3");
        smartPhoneModel22.setColor("White");
        smartPhoneModel22.setPrice(16_500);
        smartPhoneModel22.setSizeHeight(10);
        smartPhoneModel22.setSizeLength(15);
        smartPhoneModel22.setSizeWidth(10);
        smartPhoneModel22.setAvailable(true);
        smartPhoneModel22.setMemory(12);
        smartPhoneModel22.setCameraNums(3);
        smartPhoneModel22.setSmartphone(smartPhone2);

        SmartPhoneModel smartPhoneModel31 = new SmartPhoneModel();
        smartPhoneModel31.setSerialNum("Ph127");
        smartPhoneModel31.setModelName("Galaxy Pro");
        smartPhoneModel31.setColor("Yellow");
        smartPhoneModel31.setPrice(36_500);
        smartPhoneModel31.setSizeHeight(10);
        smartPhoneModel31.setSizeLength(15);
        smartPhoneModel31.setSizeWidth(10);
        smartPhoneModel31.setAvailable(true);
        smartPhoneModel31.setMemory(12);
        smartPhoneModel31.setCameraNums(3);
        smartPhoneModel31.setSmartphone(smartPhone3);

        SmartPhoneModel smartPhoneModel32 = new SmartPhoneModel();
        smartPhoneModel32.setSerialNum("Ph127");
        smartPhoneModel32.setModelName("Galaxy Ultra");
        smartPhoneModel32.setColor("Green");
        smartPhoneModel32.setPrice(36_500);
        smartPhoneModel32.setSizeHeight(10);
        smartPhoneModel32.setSizeLength(15);
        smartPhoneModel32.setSizeWidth(20);
        smartPhoneModel32.setAvailable(true);
        smartPhoneModel32.setMemory(12);
        smartPhoneModel32.setCameraNums(3);
        smartPhoneModel32.setSmartphone(smartPhone3);

        smartPhoneRepository.save(smartPhone1);
        smartPhoneRepository.save(smartPhone2);
        smartPhoneRepository.save(smartPhone3);

        smartPhoneModelRepository.save(smartPhoneModel11);
        smartPhoneModelRepository.save(smartPhoneModel12);
        smartPhoneModelRepository.save(smartPhoneModel21);
        smartPhoneModelRepository.save(smartPhoneModel22);
        smartPhoneModelRepository.save(smartPhoneModel31);
        smartPhoneModelRepository.save(smartPhoneModel32);

    }

    private void uploadTV() {
        TV tv1 = new TV();
        tv1.setId(1);
        tv1.setDeviceName("Apple");
        tv1.setCountry("USA");
        tv1.setManufacturer("Apple Co.");
        tv1.setOnlineOrderAvailable(true);
        tv1.setInstallmentAvailable(true);


        TV tv2 = new TV();
        tv2.setId(2);
        tv2.setDeviceName("Philips");
        tv2.setCountry("China");
        tv2.setManufacturer("Philips Co.");
        tv2.setOnlineOrderAvailable(false);
        tv2.setInstallmentAvailable(true);

        TV tv3 = new TV();
        tv3.setId(3);
        tv3.setDeviceName("Telcon");
        tv3.setCountry("China");
        tv3.setManufacturer("Telcon Ind.");
        tv3.setOnlineOrderAvailable(true);
        tv3.setInstallmentAvailable(true);


        TVModel tvModel11 = new TVModel();
        tvModel11.setSerialNum("S1");
        tvModel11.setModelName("MacPro");
        tvModel11.setColor("Black");
        tvModel11.setPrice(135_000);
        tvModel11.setSizeHeight(10);
        tvModel11.setSizeLength(15);
        tvModel11.setSizeWidth(10);
        tvModel11.setAvailable(true);
        tvModel11.setCategory("home");
        tvModel11.setTechnology("IPS");
        tvModel11.setTv(tv1);

        TVModel tvModel12 = new TVModel();
        tvModel12.setSerialNum("S2");
        tvModel12.setModelName("MacPro 11");
        tvModel12.setColor("White");
        tvModel12.setPrice(135_000);
        tvModel12.setSizeHeight(10);
        tvModel12.setSizeLength(15);
        tvModel12.setSizeWidth(10);
        tvModel12.setAvailable(true);
        tvModel12.setCategory("home");
        tvModel12.setTechnology("IPS");
        tvModel12.setTv(tv1);

        TVModel tvModel21 = new TVModel();
        tvModel21.setSerialNum("S3");
        tvModel21.setModelName("PHIL 11S");
        tvModel21.setColor("White");
        tvModel21.setPrice(13_000);
        tvModel21.setSizeHeight(100);
        tvModel21.setSizeLength(150);
        tvModel21.setSizeWidth(10);
        tvModel21.setAvailable(true);
        tvModel21.setCategory("home");
        tvModel21.setTechnology("IPS");
        tvModel21.setTv(tv2);

        TVModel tvModel22 = new TVModel();
        tvModel22.setSerialNum("S24");
        tvModel22.setModelName("PHIL 12S");
        tvModel22.setColor("White");
        tvModel22.setPrice(13_000);
        tvModel22.setSizeHeight(100);
        tvModel22.setSizeLength(150);
        tvModel22.setSizeWidth(12);
        tvModel22.setAvailable(true);
        tvModel22.setCategory("home");
        tvModel22.setTechnology("IPS+");
        tvModel22.setTv(tv2);

        TVModel tvModel32 = new TVModel();
        tvModel32.setSerialNum("S25");
        tvModel32.setModelName("XDF12S");
        tvModel32.setColor("BLack");
        tvModel32.setPrice(10_000);
        tvModel32.setSizeHeight(100);
        tvModel32.setSizeLength(150);
        tvModel32.setSizeWidth(12);
        tvModel32.setAvailable(true);
        tvModel32.setCategory("table");
        tvModel32.setTechnology("IPS+");
        tvModel32.setTv(tv3);

        TVModel tvModel31 = new TVModel();
        tvModel31.setSerialNum("S26");
        tvModel31.setModelName("XDF12S");
        tvModel31.setColor("White");
        tvModel31.setPrice(10_000);
        tvModel31.setSizeHeight(100);
        tvModel31.setSizeLength(150);
        tvModel31.setSizeWidth(12);
        tvModel31.setAvailable(true);
        tvModel31.setCategory("home");
        tvModel31.setTechnology("IPS+");
        tvModel31.setTv(tv3);

        tvRepository.save(tv1);
        tvRepository.save(tv2);
        tvRepository.save(tv3);

        tvModelRepository.save(tvModel11);
        tvModelRepository.save(tvModel12);
        tvModelRepository.save(tvModel21);
        tvModelRepository.save(tvModel22);
        tvModelRepository.save(tvModel31);
        tvModelRepository.save(tvModel32);
    }

    private void uploadVacuum() {
        Vacuum vacuum1 = new Vacuum();
        vacuum1.setId(1);
        vacuum1.setDeviceName("Philips");
        vacuum1.setCountry("USA");
        vacuum1.setManufacturer("Philips Co.");
        vacuum1.setOnlineOrderAvailable(true);
        vacuum1.setInstallmentAvailable(true);

        Vacuum vacuum2 = new Vacuum();
        vacuum2.setId(2);
        vacuum2.setDeviceName("Cool");
        vacuum2.setCountry("USA");
        vacuum2.setManufacturer("Qwalcom Co.");
        vacuum2.setOnlineOrderAvailable(true);
        vacuum2.setInstallmentAvailable(true);

        Vacuum vacuum3 = new Vacuum();
        vacuum3.setId(3);
        vacuum3.setDeviceName("Refreger");
        vacuum3.setCountry("USA");
        vacuum3.setManufacturer("REFGG Co.");
        vacuum3.setOnlineOrderAvailable(true);
        vacuum3.setInstallmentAvailable(true);


        VacuumModel vacuumModel11 = new VacuumModel();
        vacuumModel11.setSerialNum("V1");
        vacuumModel11.setModelName("Philips V1");
        vacuumModel11.setColor("Grey");
        vacuumModel11.setPrice(18_000);
        vacuumModel11.setSizeHeight(30);
        vacuumModel11.setSizeLength(30);
        vacuumModel11.setSizeWidth(15);
        vacuumModel11.setAvailable(true);
        vacuumModel11.setContainerVolume(1.2);
        vacuumModel11.setVacuum(vacuum1);

        VacuumModel vacuumModel12 = new VacuumModel();
        vacuumModel12.setSerialNum("V2");
        vacuumModel12.setModelName("Philips V2");
        vacuumModel12.setColor("Black");
        vacuumModel12.setPrice(14_000);
        vacuumModel12.setSizeHeight(30);
        vacuumModel12.setSizeLength(30);
        vacuumModel12.setSizeWidth(15);
        vacuumModel12.setAvailable(true);
        vacuumModel12.setContainerVolume(1.0);
        vacuumModel12.setVacuum(vacuum1);

        VacuumModel vacuumModel21 = new VacuumModel();
        vacuumModel21.setSerialNum("V13");
        vacuumModel21.setModelName("Cool V2");
        vacuumModel21.setColor("Black");
        vacuumModel21.setPrice(14_000);
        vacuumModel21.setSizeHeight(30);
        vacuumModel21.setSizeLength(30);
        vacuumModel21.setSizeWidth(15);
        vacuumModel21.setAvailable(true);
        vacuumModel21.setContainerVolume(1.0);
        vacuumModel21.setVacuum(vacuum2);

        VacuumModel vacuumModel22 = new VacuumModel();
        vacuumModel22.setSerialNum("V14");
        vacuumModel22.setModelName("Cool C2");
        vacuumModel22.setColor("Black");
        vacuumModel22.setPrice(15_000);
        vacuumModel22.setSizeHeight(30);
        vacuumModel22.setSizeLength(30);
        vacuumModel22.setSizeWidth(15);
        vacuumModel22.setAvailable(true);
        vacuumModel22.setContainerVolume(1.2);
        vacuumModel22.setVacuum(vacuum2);

        VacuumModel vacuumModel31 = new VacuumModel();
        vacuumModel31.setSerialNum("V15");
        vacuumModel31.setModelName("RCF");
        vacuumModel31.setColor("Black");
        vacuumModel31.setPrice(17_000);
        vacuumModel31.setSizeHeight(30);
        vacuumModel31.setSizeLength(30);
        vacuumModel31.setSizeWidth(15);
        vacuumModel31.setAvailable(true);
        vacuumModel31.setContainerVolume(1.4);
        vacuumModel31.setVacuum(vacuum3);

        VacuumModel vacuumModel32 = new VacuumModel();
        vacuumModel32.setSerialNum("V16");
        vacuumModel32.setModelName("RCF");
        vacuumModel32.setColor("Black");
        vacuumModel32.setPrice(13_000);
        vacuumModel32.setSizeHeight(30);
        vacuumModel32.setSizeLength(30);
        vacuumModel32.setSizeWidth(15);
        vacuumModel32.setAvailable(true);
        vacuumModel32.setContainerVolume(1.7);
        vacuumModel32.setVacuum(vacuum3);

        vacuumRepository.save(vacuum1);
        vacuumRepository.save(vacuum2);
        vacuumRepository.save(vacuum3);

        vacuumModelRepository.save(vacuumModel11);
        vacuumModelRepository.save(vacuumModel12);
        vacuumModelRepository.save(vacuumModel21);
        vacuumModelRepository.save(vacuumModel22);
        vacuumModelRepository.save(vacuumModel31);
        vacuumModelRepository.save(vacuumModel32);
    }

}
