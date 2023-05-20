
export const AuthHeader = () => {
    const token = localStorage.getItem('token');
    return { Authorization: 'Bearer ' + token };
}