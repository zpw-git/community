package com.zpw.community.sevice;

import com.zpw.community.dto.CommentListDTO;
import com.zpw.community.model.Comment;
import java.util.List;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/13-22:23
 */
public interface CommentService {

  void addComment(Comment comment);

  List<CommentListDTO> queryAllComment(Long id);
}
