package com.example.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardServices boardServices;

    //BoardDAO boardDAO;
    @RequestMapping(value = "board/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", boardServices.getBoardList());
        return "list";
    }

    @RequestMapping(value = "board/add", method = RequestMethod.GET)
    public String addPost(){
        return "addpostform";
    }

    @RequestMapping(value = "board/addok", method = RequestMethod.POST)
    public String addPostOk(BoardVO vo) {
        int i = boardServices.insertBoard(vo);
        if(i == 0){
            System.out.println("데이터 추가 성공!");
        } else {
            System.out.println("데이터 추가 실패.");
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/board/editpost/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") int id, Model model) {
        BoardVO boardVO = boardServices.getBoard(id);
        model.addAttribute("boardVO", boardVO);
        return "editpost";
    }

    @RequestMapping(value = "/board/editok", method = RequestMethod.POST)
    public String editPostOk(BoardVO vo) {
        int i = boardServices.updateBoard(vo);
        if(i == 0){
            System.out.println("데이터 수정 성공!");
        } else {
            System.out.println("데이터 수정 실패.");
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/board/delete/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") int id) {
        int i = boardServices.deleteBoard(id);
        if(i == 0){
            System.out.println("데이터 삭제 성공!");
        } else {
            System.out.println("데이터 삭제 실패.");
        }
        return "redirect:../list";
    }
}
