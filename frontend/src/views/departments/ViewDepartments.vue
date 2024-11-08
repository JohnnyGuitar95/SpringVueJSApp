<template>
  <main>
    <Navbar />
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-center">Справочник отделов</h1>
          <a href="/departmentsApi/add" class="btn btn-primary">Добавить отдел</a>
          <table class="table table-striped">
            <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Название</th>
              <th scope="col">Примечание</th>
              <th scope="col">Родительский отдел</th>
              <th scope="col">Действия</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="department in departments" :key="department.id">
              <th scope="row">{{ department.id }}</th>
              <td>{{ department.name }}</td>
              <td>{{ department.note }}</td>
              <td>{{ department.parentDepartment ? department.parentDepartment.name : 'Нет' }}</td>
              <td>
                <a class="btn btn-primary" :href="`/departmentsApi/edit/${department.id}`">Изменить</a>
                <button class="btn btn-danger mx-2" @click="deleteDepartment(department.id)">Удалить</button>
              </td>
            </tr>
            <tr v-if="!departments.length">
              <td colspan="5" class="text-center">Нет доступных отделов</td>
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
  name: 'ViewDepartments',
  components: {
    Navbar
  },
  data() {
    return {
      departments: [],
      message: ''
    };
  },

  beforeMount() {
    this.getDepartments();
  },

  methods: {
    getDepartments() {
      fetch('http://localhost:7000/departmentsApi/departments')
          .then(res => {
            if (!res.ok) {
              throw new Error('Ошибка при получении данных об отделах');
            }
            return res.json();
          })
          .then(data => {
            this.departments = data.map(department => {
              return {
                ...department,
                parentDepartment: department.parentDepartment || null
              };
            });
            console.log(data);
          })
          .catch(error => {
            console.error('Error fetching departments:', error);
            this.message = 'Не удалось загрузить отделы';
          });
    },

    deleteDepartment(id) {
      if (confirm('Вы уверены, что хотите удалить этот отдел?')) {
        fetch(`http://localhost:7000/departmentsApi/department/${id}`, {
          method: 'DELETE'
        })
            .then(response => {
              if (response.ok) {
                this.message = 'Отдел успешно удален!';
                this.getDepartments();
              } else {
                throw new Error('Ошибка при удалении отдела');
              }
            })
            .catch(error => {
              console.error('Error deleting department:', error);
              this.message = 'Не удалось удалить отдел';
            });
      }
    }
  }
}
</script>