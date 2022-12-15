package com.example.util;


import com.example.board.BoardDAO;
import com.example.board.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class FileUpload {
    public BoardVO uploadPhoto(HttpServletRequest request){
        String filename = "";
        int sizeLimit = 15 * 1024 * 1024;

        String realPath = request.getServletContext().getRealPath("resources/img");

        File dir = new File(realPath);
        if (!dir.exists()) dir.mkdirs();

        BoardVO one = null;
        MultipartRequest multipartRequest = null;
        try{
            multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

            filename = multipartRequest.getFilesystemName("imagefile");
            System.out.println(filename);
            one = new BoardVO();
            String seq = multipartRequest.getParameter("seq");
            if(seq!=null&&!seq.equals("")) one.setSeq(Integer.parseInt(seq));
            one.setPw(multipartRequest.getParameter("pw"));
            one.setNickname(multipartRequest.getParameter("nickname"));
            one.setContent(multipartRequest.getParameter("content"));
            one.setIp(multipartRequest.getParameter("ip"));
            if(seq!=null&&!seq.equals("")){
                BoardDAO dao = new BoardDAO();
                String oldfilename = dao.getBoard(Integer.parseInt(seq)).getImage();
                if(filename != null && oldfilename != null)
                    FileUpload.deleteFile(request, oldfilename);
                else if (filename == null && oldfilename!=null)
                    filename = oldfilename;
            }

            one.setImage(filename);
        } catch (Exception e){
            e.printStackTrace();
        }
        return one;
    }

    public static void deleteFile(HttpServletRequest request, String filename){
        String filePath = request.getServletContext().getRealPath("upload");

        File f = new File(filePath + "/" + filename);
        if( f.exists() ) f.delete();
    }
}
