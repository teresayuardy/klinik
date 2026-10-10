package com.example.klinik.entity;


import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.validation.constraints.Max;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dokter extends Insan {
    //Deklarasi variabel
    @Size(min = 1, max = 6)
    private String idDokter;

    @Min(1000000)
    @Max(999999999)
    private Long gaji;

    @Min(10000)
    @Max(999999)
    private Long tarif;

    @Size(min = 1, max = 6)
    private String ruangPraktik;

    @Size(min = 1, max = 4)
    private String tipe;

    // Constructor

    public Dokter() {}

    public Dokter(String tipe, String idDokter, String nama, int umur, String telp, long gaji, long tarif,
            String ruangPraktik) {
        super(nama, umur, telp);
        this.tipe = tipe;
        this.idDokter = idDokter;
        this.gaji = gaji;
        this.tarif = tarif;
        this.ruangPraktik = ruangPraktik;
    }

    // Setter & getter
    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getTipe() {
        return tipe;
    }

    public void setIdDokter(String id) {
        this.idDokter = id;
    }
    public String getIdDokter() {
        return idDokter;
    }

    public void setGaji(long gaji) {
        this.gaji = gaji;
    }

    public long getGaji() {
        return gaji;
    }

    public void setTarif(long tarif) {
        this.tarif = tarif;
    }

    public long getTarif() {
        return tarif;
    }

    public void setRuangPraktik(String ruangPraktik) {
        this.ruangPraktik = ruangPraktik;
    }

    public String getRuangPraktik() {
        return ruangPraktik;
    }

    //=================================================KODE UTS=========================================================
    // public void tampilkanSpesialisasi() {
    //     System.out.print("\n");
    //     System.out.println("ID: " + idDokter);
    //     super.tampilkanSpesialisasi();
    //     System.out.println("Gaji Dokter: " + gaji);
    //     System.out.println("Tarif Dokter: " + tarif);
    //     System.out.println("Ruang Praktik Dokter: " + ruangPraktik);
    // }

    // public static List<Dokter> bacaDokter(String namaFile) {
    //     List<Dokter> dokter_list = new ArrayList<>();

    //     try {
    //         File file = new File(namaFile);
    //         Scanner fileScanner = new Scanner(file);

    //         while (fileScanner.hasNextLine()) {
    //             String baris = fileScanner.nextLine();
    //             if (baris.trim().isEmpty())
    //                 continue;

    //             String[] data = baris.split(",");
    //             if (data.length < 2)
    //                 continue;

    //             String tipe = data[0].trim().toUpperCase();
    //             String Id = data[1].trim();
    //             String nama = data[2].trim();
    //             Integer umur = Integer.parseInt(data[3].trim());
    //             String telp = data[4].trim();
    //             long gaji = Long.parseLong(data[5].trim());
    //             long tarif = Long.parseLong(data[6].trim());
    //             String ruangPraktik = data[7].trim();

    //             Dokter dokterBaru = null;
    //             if (tipe.equals("UMUM")) {
    //                 Boolean bisaMerujuk = Boolean.parseBoolean(data[8].trim());
    //                 dokterBaru = new DokterUmum(tipe, Id, nama, umur, telp, gaji, tarif, ruangPraktik, bisaMerujuk);
    //             } else if (tipe.equals("GIGI")) {
    //                 Boolean bedahMulut = Boolean.parseBoolean(data[8].trim());
    //                 dokterBaru = new DokterGigi(tipe, Id, nama, umur, telp, gaji, tarif, ruangPraktik, bedahMulut);
    //             }

    //             if (dokterBaru != null) {
    //                 dokter_list.add(dokterBaru);
    //             }
    //         }
    //         fileScanner.close();
    //     } catch (FileNotFoundException e) {
    //         System.err.println("ERROR: File '" + namaFile + "' tidak ditemukan.");
    //     }
    //     return dokter_list;
    // }

    // public static Dokter getDokterById(List<Dokter> dokter_list, String id) {
    //     for (Dokter d : dokter_list) {
    //         if (d.getIdDokter().equals(id)) {
    //             return d;
    //         }
    //     }
    //     return null;
    // }
}