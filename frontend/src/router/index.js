import { createRouter, createWebHistory } from 'vue-router';
import ViewProfessions from "@/views/professions/ViewProfessions.vue";
import ViewEmployees from "@/views/employees/ViewEmployees.vue";
import ViewDepartments from "@/views/departments/ViewDepartments.vue";

const routes = [
    {
        path: '/',
        name: 'home',
        component: ViewProfessions
    },
    {
        path: '/professionsHome',
        name: 'professions',
        component: ViewProfessions
    },
    {
        path: '/employeesHome',
        name: 'employees',
        component: ViewEmployees
    },
    {
        path: '/departmentsHome',
        name: 'departments',
        component: ViewDepartments
    },
    {
        path: '/professionsApi/add',
        name: 'addProfession',
        component: () => import('../views/professions/AddProfession.vue')
    },
    {
        path: '/professionsApi/edit/:id',
        name: 'editProfession',
        component: () => import('../views/professions/UpdateProfession.vue')
    },
    {
        path: '/employeesApi/add',
        name: 'addEmployee',
        component: () => import('../views/employees/AddEmployee.vue')
    },
    {
        path: '/employeesApi/edit/:id',
        name: 'editEmployee',
        component: () => import('../views/employees/UpdateEmployee.vue')
    },
    {
        path: '/departmentsApi/add',
        name: 'addDepartment',
        component: () => import('../views/departments/AddDepartment.vue')
    },
    {
        path: '/departmentsApi/edit/:id',
        name: 'editDepartment',
        component: () => import('../views/departments/UpdateDepartment.vue')
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
});

export default router;