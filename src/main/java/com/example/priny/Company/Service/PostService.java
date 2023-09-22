package com.example.priny.Company.Service;

import com.example.priny.Company.Dto.PostResponseDTO;
import com.example.priny.Company.Dto.PostSaveRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostResponseDTO findById(Long id);
    public List<PostResponseDTO> findAllJobPosting();
    //JobPostResponseDTO edit(Long id, PostEditDTO postEditDTO);
    public void deleteById(Long id);
    Long JobPostSave(String userId, PostSaveRequestDTO postSaveRequestDTO);

}
