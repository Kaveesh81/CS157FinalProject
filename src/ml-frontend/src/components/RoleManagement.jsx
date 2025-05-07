import {
    Box, Typography, TableContainer, Table, TableHead,
    TableBody, TableRow, TableCell, Paper, Select, MenuItem
  } from '@mui/material';
  
  export default function RoleManagement({ members, onRoleChange }) {
    const roles = ["General", "Project Member", "Project Lead", "Manager"];
  
    return (
      <Box sx={{ mt:4 }}>
        <Typography variant="h5" gutterBottom>Manage Member Roles</Typography>
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow sx={{ backgroundColor: 'grey.200' }}>
                <TableCell>Name</TableCell>
                <TableCell>Student ID</TableCell>
                <TableCell>Role</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {members.map((m) => (
                <TableRow key={m.id}>
                  <TableCell>{m.name}</TableCell>
                  <TableCell>{m.id}</TableCell>
                  <TableCell>
                    <Select
                      value={m.role}
                      onChange={e => onRoleChange(m.id, e.target.value)}
                      fullWidth
                    >
                      {roles.map(r => (
                        <MenuItem key={r} value={r}>{r}</MenuItem>
                      ))}
                    </Select>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
    );
  }
  