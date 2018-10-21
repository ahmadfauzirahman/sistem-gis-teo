package com.example.macbook.teo.rest;

import com.example.macbook.teo.response.ResponseLokasiPematang;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by radinaldn on 18/04/18.
 */

public interface ApiInterface {

    // di sini adalah penghubung antara android dan server

    // untuk login
   /* @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> login(
            @Field("nip_nim") String nip_nim,
            @Field("password") String password
    );

    @GET("pengumuman")
    Call<ResponsePengumuman> pengumuman();


    //api abid
    @GET("mobile/jadwal-seminar")
    Call<ResponseJadwalSeminar> jadwalseminar();


    @GET("pengajuanta")
    Call<ResponsePengajuanKor> pengajuanta();

    @GET("pengajuanta/{id_pengajuan}")
    Call<ResponsePengajuanKor> pengajuantadetail(@Path("id_pengajuan") String id_pengajuan);


    @GET("semha")
    Call<ResponseSemha> semha();

    @GET("semhadetail/{id_semha}")
    Call<ResponseSemha> semhadetail(@Path("id_semha") String id_semha);

    @GET("proposal")
    Call<ResponseSeminarProposal> proposal();


    @GET("proposaldetail/{id_proposal}")
    Call<ResponseSeminarProposal> proposaldetail(@Path("id_proposal") String id_proposal);


    @GET("mobile/jadwal-bimbingan/{hak_akses}/{nip_nim}")
    Call<ResponseGetJadwalBimbingan> jadwalbimbinganku(@Path("hak_akses") String hak_akses, @Path("nip_nim") String nip_nim);

    @GET("mobile/jadwal-set-bimbingan/{id_pembimbing}/{nim}")
    Call<ResponseJadwalSetBimbingan> setJadwalbimbinganpertama(@Path("id_pembimbing") String id_pembimbing, @Path("nim") String nim);

    @GET("bimbinganku/{nip}")
    Call<ResponseSeminarProposal> proposaldetaildosen(@Path("nip") String nip);

    @GET("reviewku/{nip}")
    Call<ResponsePengajuanKor> reviewkudetaildosen(@Path("nip") String nip);

    @GET("dosen")
    Call<ResponseDosen> dosen();




    //untuk signup
    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseBody> signup(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama_lengkap") String namaLengkap,
            @Field("email") String email,
            @Field("no_hp") String nomorHP
    );

    @FormUrlEncoded
    @POST("mobile/post-histori-bimbingan")
    Call<ResponseAbsensi> masukkandata(
            @Field("idJadwalBimbingan") String idJadwalBimbingan,
            @Field("nim") String nim,
            @Field("tanggal") String tanggal,
            @Field("selesai") String selesai,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("mobile/request-jadwal-bimbingan")
    Call<ResponseBody> masukandata(
            @Field("nim_mahasiswa") String nim_mahasiswa,
            @Field("nip_dosen_pembimbing") String nip_dosen_pembimbing,
            @Field("deskripsi_bimbingan") String deskripsi_bimbingan,
            @Field("tanggal") String tanggal,
            @Field("mulai") String mulai,
            @Field("selesai") String selesai
    );

    //untuk pemesanan
    @FormUrlEncoded
    @POST("do_pemesanan.php")
    Call<ResponseBody> do_pemesanan(
            @Field("makanan") String makanan,
            @Field("porsi") String porsi,
            @Field("ket") String ket,
            @Field("lat") String lat,
            @Field("lng") String lng
    );*/

    @GET("lokasi-penting-api/all")
    Call<ResponseLokasiPematang> lokasi();

    @GET("lokasi-penting-api/detail?=id")
    Call<ResponseLokasiPematang> detail(@Query("id") String id);

    @FormUrlEncoded
    @POST("lokasi-penting-api/laporan")
    Call<ResponseBody> laporankan(
            @Field("satu") String satu,
            @Field("dua") String dua,
            @Field("tiga") String tiga,
            @Field("empat") String empat,
            @Field("lima") String lima);


}