import RoleManagement from '../components/RoleManagement';

export default function Admin({ members, onRoleChange }) {
  return <RoleManagement members={members} onRoleChange={onRoleChange} />;
}
