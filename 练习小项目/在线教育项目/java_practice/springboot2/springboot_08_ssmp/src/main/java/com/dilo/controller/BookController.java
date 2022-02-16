package com.dilo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dilo.controller.utils.Result;
import com.dilo.domain.Book;
import com.dilo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public Result getAll(){
        return new Result(true,bookService.list());
    }

    @PostMapping
    public Result save(@RequestBody Book book){

        return new Result(bookService.save(book));
    }

    @PutMapping
    public Result update(@RequestBody Book book){
        return new Result(bookService.updateById(book));
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return new Result(bookService.removeById(id));

    }

    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id){
        return new Result(true,bookService.getById(id));
    }

    //@GetMapping("/{currentPage}/{pageSize}")
    //public Result getPage(@PathVariable int currentPage,@PathVariable int pageSize){
    //    IPage<Book> page = bookService.getPage(currentPage, pageSize);
    //    if (currentPage > page.getPages()){
    //        page = bookService.getPage((int) page.getPages(), pageSize);
    //    }
    //
    //    return new Result(true,page);
    //}

    @GetMapping("/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable int currentPage,@PathVariable int pageSize,Book book){

        System.out.println(book);
        IPage<Book> page = bookService.getPage(currentPage, pageSize,book);
        if (currentPage > page.getPages()){
            page = bookService.getPage((int) page.getPages(), pageSize,book);
        }



        return new Result(true,page);
    }
}
