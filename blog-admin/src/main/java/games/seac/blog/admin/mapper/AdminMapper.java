package games.seac.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import games.seac.blog.admin.pojo.Admin;
import games.seac.blog.admin.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Honmono
 * @date 2022/2/12 - 16:46
 */
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from ms_permission where id in (select permission_id from ms_admin_permission where admin_id=#{adminId})")
    List<Permission> findPermissionByAdminId(Long id);
}
