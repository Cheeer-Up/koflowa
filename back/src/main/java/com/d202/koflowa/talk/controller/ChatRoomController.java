package com.d202.koflowa.talk.controller;

import com.d202.koflowa.talk.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
public class ChatRoomController {
    private final ChatRoomRepository chatRoomRepository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms(){

        System.out.println("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("data", chatRoomRepository.findAllRooms());

        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(RedirectAttributes rttr){

        System.out.println("# Create Chat Room");
        rttr.addFlashAttribute("roomName", chatRoomRepository.createRoomDto());
        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(String roomId, Model model){

        System.out.println("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", chatRoomRepository.findRoomById(roomId));
    }
}
