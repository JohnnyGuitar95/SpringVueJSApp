<template>
  <main>
    <Navbar />
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-center">Справочник сотрудников</h1>
          <a href="/employeesApi/add" class="btn btn-primary">Добавить сотрудника</a>
          <table class="table table-striped">
            <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">ФИО</th>
              <th scope="col">Примечание</th>
              <th scope="col">Отдел</th>
              <th scope="col">Профессия</th>
              <th scope="col">Действия</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="employee in employees" :key="employee.id">
              <th scope="row">{{ employee.id }}</th>
              <td>{{ employee.name }}</td>
              <td>{{ employee.note }}</td>
              <td>{{ employee.department ? employee.department.name : 'Нет' }}</td>
              <td>{{ employee.profession ? employee.profession.name : 'Нет' }}</td>
              <td>
                <a class="btn btn-primary" :href="`/employeesApi/edit/${employee.id}`">Изменить</a>
                <button class="btn btn-danger mx-2" @click="deleteEmployee(employee.id)">Удалить</button>
              </td>
            </tr>
            <tr v-if="!employees.length">
              <td colspan="6" class="text-center">Нет доступных сотрудников</td>
            </tr>
            </tbody>
          </table>
          <p v-if="message" class="text-success">{{ message }}</p>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../../components/Navbar.vue';

export default {
  name: 'ViewEmployees',
  components: {
    Navbar
  },
  data() {
    return {
      employees: [],
      message: ''
    };
  },

  beforeMount() {
    this.getEmployees();
  },

  methods: {
    getEmployees() {
      fetch('http://localhost:7000/employeesApi/employees')
          .then(res => {
            if (!res.ok) {
              throw new Error('Ошибка при получении данных о сотрудниках');
            }
            return res.json();
          })
          .then(data => {
            this.employees = data.map(employee => {
              return {
                ...employee,
                department: employee.department || null,
                profession: employee.profession || null
              };
            });
            console.log(data);
          })
          .catch(error => {
            console.error('Error fetching employees:', error);
            this.message = 'Не удалось загрузить сотрудников';
          });
    },

    deleteEmployee(id) {
      if (confirm('Вы уверены, что хотите удалить этого сотрудника?')) {
        fetch(`http://localhost:7000/employeesApi/employee/${id}`, {
          method: 'DELETE'
        })
            .then(response => {
              if (response.ok) {
                this.message = 'Сотрудник успешно удален!';
                this.getEmployees();
              } else {
                console.error('Ошибка при удалении сотрудника');
              }
            })
            .catch(error => {
              console.error('Error deleting employee:', error);
              this.message = 'Не удалось удалить сотрудника.';
            });
      }
    }
  }
}
</script>